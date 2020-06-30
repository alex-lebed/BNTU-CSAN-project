package by.bntu.fitr.isats.quiz.util;

import by.bntu.fitr.isats.quiz.entity.game.GameStatus;
import by.bntu.fitr.isats.quiz.entity.game.Lobby;
import by.bntu.fitr.isats.quiz.entity.question.Question;
import by.bntu.fitr.isats.quiz.entity.user.Admin;
import by.bntu.fitr.isats.quiz.entity.user.Player;
import by.bntu.fitr.isats.quiz.entity.user.Winner;
import by.bntu.fitr.isats.quiz.exception.EntityNotFoundException;
import by.bntu.fitr.isats.quiz.exception.ServiceException;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameLogic {

    private static final int INITIAL_QUESTION_INDEX = 0;
    private static final int SCORE_PER_QUESTION = 1;

    private static final int FIRST_PLAYER_ID = 0;
    private static final int INITIAL_PLAYER_SCORE = 0;

    public Lobby createLobby(Admin admin,
                             String password,
                             int playersAmount,
                             List<Question> questions) {
        List<Player> playerList = new ArrayList<>(playersAmount);
        return Lobby.builder()
                .admin(admin)
                .password(password)
                .playersAmountToStart(playersAmount)
                .players(playerList)
                .questions(questions)
                .currentQuestionIndex(INITIAL_QUESTION_INDEX)
                .status(GameStatus.WAITING_PLAYERS)
                .build();
    }

    public void connectPlayer(String playerName, Lobby lobby, String submittedPassword) throws ServiceException {
        String lobbyPassword = lobby.getPassword();
        if (!lobbyPassword.equals(submittedPassword)) {
            throw new ServiceException(
                    "Invalid password: " + submittedPassword +
                            ", player name: " + playerName +
                            ", lobby id: " + lobby.getId()
            );
        }

        if (lobby.getStatus() != GameStatus.WAITING_PLAYERS) {
            throw new ServiceException("Can't connect player to lobby, game status: " + lobby.getStatus());
        }

        Player player = createPlayer(lobby, playerName);
        List<Player> players = lobby.getPlayers();
        players.add(player);

        if (players.size() == lobby.getPlayersAmountToStart()) {
            lobby.setStatus(GameStatus.STARTED);
        }
    }

    public void proceed(Lobby lobby, int playerId) throws ServiceException {
        GameStatus status = lobby.getStatus();
        if (status != GameStatus.STARTED) {
            throw new ServiceException("Can't proceed. Lobby status: " + status);
        }

        Player player = lobby.getPlayers()
                .stream()
                .filter(p -> p.getId().equals(playerId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Player not found by id: " + playerId + ", lobby id: " + lobby.getId()));
        player.incrementScore(SCORE_PER_QUESTION);

        int questionIndex = lobby.getCurrentQuestionIndex();
        if (isLastQuestion(questionIndex, lobby)) {
            lobby.setStatus(GameStatus.FINISHED);
            List<Winner> winners = createWinners(lobby);
            lobby.setWinners(winners);
        } else {
            lobby.setCurrentQuestionIndex(questionIndex + 1);
        }
    }

    private Player createPlayer(Lobby lobby, String playerName) {
        int id = lobby.getPlayers()
                .stream()
                .mapToInt(p -> p.getId() + 1)
                .max()
                .orElse(FIRST_PLAYER_ID);
        return new Player(id, playerName, INITIAL_PLAYER_SCORE);
    }

    private boolean isLastQuestion(int questionIndex, Lobby lobby) {
        return questionIndex == lobby.getQuestions().size() - 1;
    }

    private List<Winner> createWinners(Lobby lobby) {
        List<Player> players = lobby.getPlayers();
        if (players.size() == 0) {
            return Collections.emptyList();
        }

        int maxScore = players.stream()
                .mapToInt(Player::getScore)
                .max()
                .getAsInt();

        Timestamp gameDate = new Timestamp(System.currentTimeMillis());
        return players.stream()
                .filter(p -> p.getScore() == maxScore)
                .map(p -> mapToWinner(p, gameDate))
                .collect(Collectors.toList());
    }

    private Winner mapToWinner(Player player, Timestamp gameDate) {
        return new Winner(player.getId(), player.getName(), gameDate, player.getScore());
    }

}
