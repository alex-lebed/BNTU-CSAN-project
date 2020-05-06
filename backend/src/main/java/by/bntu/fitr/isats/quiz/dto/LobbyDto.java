package by.bntu.fitr.isats.quiz.dto;

import by.bntu.fitr.isats.quiz.entity.user.Admin;
import by.bntu.fitr.isats.quiz.entity.game.GameStatus;
import by.bntu.fitr.isats.quiz.entity.question.Question;
import by.bntu.fitr.isats.quiz.entity.user.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class LobbyDto {

    private Integer id;
    private Admin admin;
    private String password;
    private List<Player> players;
    private int playersAmountToStart;
    private List<Question> questions;
    private int currentQuestionIndex;
    private GameStatus status;

}
