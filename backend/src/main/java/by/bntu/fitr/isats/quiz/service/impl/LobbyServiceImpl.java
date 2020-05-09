package by.bntu.fitr.isats.quiz.service.impl;

import by.bntu.fitr.isats.quiz.LobbyPool;
import by.bntu.fitr.isats.quiz.dao.api.AdminDao;
import by.bntu.fitr.isats.quiz.dao.api.WinnerDao;
import by.bntu.fitr.isats.quiz.dto.LobbyConfigDto;
import by.bntu.fitr.isats.quiz.dto.LobbyDto;
import by.bntu.fitr.isats.quiz.dto.PlayerConnectDto;
import by.bntu.fitr.isats.quiz.entity.user.Admin;
import by.bntu.fitr.isats.quiz.entity.game.GameStatus;
import by.bntu.fitr.isats.quiz.entity.game.Lobby;
import by.bntu.fitr.isats.quiz.entity.user.Player;
import by.bntu.fitr.isats.quiz.entity.question.Question;
import by.bntu.fitr.isats.quiz.entity.user.Winner;
import by.bntu.fitr.isats.quiz.exception.EntityNotFoundException;
import by.bntu.fitr.isats.quiz.exception.ServiceException;
import by.bntu.fitr.isats.quiz.service.api.LobbyService;
import by.bntu.fitr.isats.quiz.service.api.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LobbyServiceImpl implements LobbyService {

    private static final int INITIAL_QUESTION_INDEX = 0;
    private static final int FIRST_PLAYER_ID = 0;
    private static final int INITIAL_PLAYER_SCORE = 0;
    private static final int SCORE_PER_QUESTION = 1;

    private ModelMapper mapper;
    private AdminDao adminDao;
    private WinnerDao winnerDao;
    private QuestionService questionService;

    @Autowired
    public LobbyServiceImpl(ModelMapper mapper,
                            AdminDao adminDao, WinnerDao winnerDao,
                            QuestionService questionService) {
        this.mapper = mapper;
        this.adminDao = adminDao;
        this.winnerDao = winnerDao;
        this.questionService = questionService;
    }

    @Override
    public LobbyDto createLobby(LobbyConfigDto dto) throws ServiceException {
        Lobby lobby = generateLobby(dto);
        LobbyPool pool = LobbyPool.getInstance();
        pool.addLobby(lobby);
        return mapper.map(lobby, LobbyDto.class);
    }

    @Override
    public LobbyDto connectPlayer(int lobbyId, PlayerConnectDto dto) throws ServiceException {
        Lobby lobby = getLobby(lobbyId);
        synchronized (lobby) {
            String lobbyPassword = lobby.getPassword();
            String submittedPassword = dto.getLobbyPassword();
            if (!lobbyPassword.equals(submittedPassword)) {
                throw new ServiceException(
                        "Invalid password: " + submittedPassword +
                        ", player name: " + dto.getName() +
                        ", lobby id: " + lobby.getId()
                );
            }

            if (lobby.getStatus() != GameStatus.WAITING_PLAYERS) {
                throw new ServiceException("Can't connect player to lobby, game status: " + lobby.getStatus());
            }

            Player player = createPlayer(dto, lobby);
            List<Player> players = lobby.getPlayers();
            players.add(player);

            if (players.size() == lobby.getPlayersAmountToStart()) {
                lobby.setStatus(GameStatus.STARTED);
            }

            return mapper.map(lobby, LobbyDto.class);
        }
    }

    @Override
    public LobbyDto proceed(int lobbyId, int playerId) throws ServiceException {
        Lobby lobby = getLobby(lobbyId);
        synchronized (lobby) {
            GameStatus status = lobby.getStatus();
            if (status != GameStatus.STARTED) {
                throw new ServiceException("Can't proceed. Lobby status: " + status);
            }

            Player player = getPlayer(playerId, lobby);
            player.incrementScore(SCORE_PER_QUESTION);

            int questionIndex = lobby.getCurrentQuestionIndex();
            if (isLast(questionIndex, lobby)) {
                lobby.setStatus(GameStatus.FINISHED);
                List<Winner> winners = getWinners(lobby);
                lobby.setWinners(winners);
                saveWinners(winners);
            } else {
                lobby.setCurrentQuestionIndex(questionIndex + 1);
            }

            return mapper.map(lobby, LobbyDto.class);
        }
    }

    @Transactional
    public void saveWinners(List<Winner> winners) {
        winners.forEach(winnerDao::save);
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

    private Lobby getLobby(int id) throws EntityNotFoundException {
        LobbyPool pool = LobbyPool.getInstance();
        return pool.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lobby not found by id: " + id));
    }

    private Player getPlayer(int id, Lobby lobby) throws EntityNotFoundException {
        return lobby.getPlayers()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Player not found by id: " + id + ", lobby id: " + lobby.getId()));
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

    private List<Winner> getWinners(Lobby lobby) {
        List<Player> players = lobby.getPlayers();
        if (players.size() == 0) {
            return Collections.emptyList();
        }

        int maxScore = players.stream()
                .mapToInt(Player::getScore)
                .max()
                .getAsInt();

        return  players.stream()
                .filter(p -> p.getScore() == maxScore)
                .map(this::mapToWinner)
                .collect(Collectors.toList());
    }

    private Winner mapToWinner(Player player) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new Winner(player.getId(), player.getName(), timestamp, player.getScore());
    }

    private boolean isLast(int questionIndex, Lobby lobby) {
        return questionIndex == lobby.getQuestions().size() - 1;
    }

    private List<Player> createListForPlayers(LobbyConfigDto config) {
        int size = config.getPlayersAmountToStart();
        return new ArrayList<>(size);
    }

    private List<Question> getRandomQuestions(LobbyConfigDto config) {
        int amount = config.getQuestionsAmount();
        return questionService.getRandomQuestions(amount);
    }

}
