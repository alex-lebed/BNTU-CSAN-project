package by.bntu.fitr.isats.quiz.service.impl;

import by.bntu.fitr.isats.quiz.LobbyPool;
import by.bntu.fitr.isats.quiz.dao.api.AdminDao;
import by.bntu.fitr.isats.quiz.dao.api.QuestionDao;
import by.bntu.fitr.isats.quiz.dto.LobbyConfigDto;
import by.bntu.fitr.isats.quiz.dto.LobbyDto;
import by.bntu.fitr.isats.quiz.dto.PlayerConnectDto;
import by.bntu.fitr.isats.quiz.entity.user.Admin;
import by.bntu.fitr.isats.quiz.entity.game.GameStatus;
import by.bntu.fitr.isats.quiz.entity.game.Lobby;
import by.bntu.fitr.isats.quiz.entity.user.Player;
import by.bntu.fitr.isats.quiz.entity.question.Question;
import by.bntu.fitr.isats.quiz.exception.EntityNotFoundException;
import by.bntu.fitr.isats.quiz.exception.ServiceException;
import by.bntu.fitr.isats.quiz.service.api.LobbyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LobbyServiceImpl implements LobbyService {

    private static final int INITIAL_QUESTION_INDEX = 0;
    private static final int FIRST_PLAYER_ID = 0;
    private static final int INITIAL_PLAYER_SCORE = 0;

    private ModelMapper mapper;
    private QuestionDao questionDao;
    private AdminDao adminDao;

    @Autowired
    public LobbyServiceImpl(ModelMapper mapper, QuestionDao questionDao, AdminDao adminDao) {
        this.mapper = mapper;
        this.questionDao = questionDao;
        this.adminDao = adminDao;
    }

    @Override
    public LobbyDto createLobby(LobbyConfigDto dto) throws ServiceException {
        Lobby lobby = generateLobby(dto);
        LobbyPool pool = LobbyPool.getInstance();
        pool.addLobby(lobby);
        return mapper.map(lobby, LobbyDto.class);
    }

    @Override
    public LobbyDto connectPlayer(PlayerConnectDto dto) throws ServiceException {
        Lobby lobby = getLobby(dto);
        Player player = createPlayer(dto, lobby);
        lobby.getPlayers()
                .add(player);
        return mapper.map(lobby, LobbyDto.class);
    }

    private Lobby generateLobby(LobbyConfigDto config) throws ServiceException {
        return Lobby.builder()
                .id(LobbyPool.getInstance().generateId())
                .admin(getAdmin(config))
                .password(config.getPassword())
                .players(createListForPlayers(config))
                .playersAmountToStart(config.getPlayersAmountToStart())
                .questions(getRandomQuestions(config))
                .currentQuestionIndex(INITIAL_QUESTION_INDEX)
                .status(GameStatus.WAITING_PLAYERS)
                .build();
    }

    private Lobby getLobby(PlayerConnectDto dto) throws EntityNotFoundException {
        int id = dto.getLobbyId();
        LobbyPool pool = LobbyPool.getInstance();
        return pool.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lobby not found by id: " + id));
    }

    private Player createPlayer(PlayerConnectDto dto, Lobby lobby) {
        int id = lobby.getPlayers()
                .stream()
                .mapToInt(p -> p.getId() + 1)
                .max()
                .orElse(FIRST_PLAYER_ID);
        String name = dto.getName();
        return new Player(id, name, INITIAL_PLAYER_SCORE);
    }

    private Admin getAdmin(LobbyConfigDto config) throws EntityNotFoundException {
        Admin admin = config.getAdmin();
        String login = admin.getLogin();
        String password = admin.getPassword();
        return adminDao.getByLoginAndPassword(login, password)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found (or password incorrect): " + login));
    }

    private List<Player> createListForPlayers(LobbyConfigDto config) {
        int size = config.getPlayersAmountToStart();
        return new ArrayList<>(size);
    }

    private List<Question> getRandomQuestions(LobbyConfigDto config) {
        int amount = config.getQuestionsAmount();
        return questionDao.getRandomQuestions(amount);
    }

}
