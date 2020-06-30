package by.bntu.fitr.isats.quiz.service.impl;

import by.bntu.fitr.isats.quiz.LobbyPool;
import by.bntu.fitr.isats.quiz.dto.LobbyConfigDto;
import by.bntu.fitr.isats.quiz.dto.LobbyDto;
import by.bntu.fitr.isats.quiz.dto.PlayerConnectDto;
import by.bntu.fitr.isats.quiz.dto.PlayerDto;
import by.bntu.fitr.isats.quiz.entity.game.GameStatus;
import by.bntu.fitr.isats.quiz.entity.game.Lobby;
import by.bntu.fitr.isats.quiz.entity.question.Question;
import by.bntu.fitr.isats.quiz.entity.user.Admin;
import by.bntu.fitr.isats.quiz.entity.user.Winner;
import by.bntu.fitr.isats.quiz.exception.ServiceException;
import by.bntu.fitr.isats.quiz.repository.AdminRepository;
import by.bntu.fitr.isats.quiz.repository.QuestionRepository;
import by.bntu.fitr.isats.quiz.repository.WinnerRepository;
import by.bntu.fitr.isats.quiz.service.api.LobbyService;
import by.bntu.fitr.isats.quiz.util.GameLogic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class LobbyServiceImpl implements LobbyService {

    private ModelMapper mapper;
    private GameLogic gameLogic;
    private AdminRepository adminRepository;
    private QuestionRepository questionRepository;
    private WinnerRepository winnerRepository;

    @Autowired
    public LobbyServiceImpl(ModelMapper mapper,
                            GameLogic gameLogic,
                            AdminRepository adminRepository,
                            QuestionRepository questionRepository,
                            WinnerRepository winnerRepository) {
        this.mapper = mapper;
        this.gameLogic = gameLogic;
        this.adminRepository = adminRepository;
        this.questionRepository = questionRepository;
        this.winnerRepository = winnerRepository;
    }

    @Override
    public LobbyDto createLobby(LobbyConfigDto dto) throws ServiceException {
        Admin admin = getAdmin(dto);
        String password = dto.getPassword();
        int playersAmount = dto.getPlayersAmountToStart();
        List<Question> questions = getRandomQuestions(dto);

        Lobby lobby = gameLogic.createLobby(admin, password, playersAmount, questions);
        LobbyPool pool = LobbyPool.getInstance();
        pool.addLobby(lobby);
        return mapToLobbyDto(lobby);
    }

    @Override
    public LobbyDto connectPlayer(int lobbyId, PlayerConnectDto dto) throws ServiceException {
        Lobby lobby = getLobby(lobbyId);
        synchronized (lobby) {
            String playerName = dto.getName();
            String submittedPassword = dto.getLobbyPassword();
            gameLogic.connectPlayer(playerName, lobby, submittedPassword);
            return mapToLobbyDto(lobby);
        }
    }

    @Override
    public LobbyDto proceed(int lobbyId, int playerId) throws ServiceException {
        Lobby lobby = getLobby(lobbyId);
        synchronized (lobby) {
            gameLogic.proceed(lobby, playerId);
            if (lobby.getStatus() == GameStatus.FINISHED) {
                List<Winner> winners = lobby.getWinners();
                winnerRepository.saveAll(winners);
            }
            return mapToLobbyDto(lobby);
        }
    }

    private Admin getAdmin(LobbyConfigDto config) {
        String login = config.getAdmin().getLogin();
        String password = config.getAdmin().getPassword();
        return adminRepository.findByLoginAndPassword(login, password)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found (or password is incorrect): " + login));
    }

    private List<Question> getRandomQuestions(LobbyConfigDto config) {
        int limit = config.getQuestionsAmount();
        Pageable pageable = PageRequest.of(0, limit); // not for pagination, but for limiting the result rows
        return questionRepository.getRandomQuestions(pageable);
    }

    private Lobby getLobby(int id) throws EntityNotFoundException {
        LobbyPool pool = LobbyPool.getInstance();
        return pool.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lobby not found by id: " + id));
    }

    private LobbyDto mapToLobbyDto(Lobby lobby) {
        LobbyDto dto = mapper.map(lobby, LobbyDto.class);
        if (lobby.getStatus() == GameStatus.FINISHED) {
            List<Winner> winners = lobby.getWinners();
            dto.getPlayers()
                    .stream()
                    .filter(playerDto -> winners.stream()
                            .anyMatch(winner -> isSamePerson(playerDto, winner)))
                    .forEach(p -> p.setWinner(true));
        }
        return dto;
    }

    private boolean isSamePerson(PlayerDto player, Winner winner) {
        return player.getId()
                .equals(winner.getLocalId());
    }

}
