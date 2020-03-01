package by.bntu.fitr.isats.quiz.dto;

import by.bntu.fitr.isats.quiz.entity.user.Admin;
import by.bntu.fitr.isats.quiz.entity.game.GameStatus;
import by.bntu.fitr.isats.quiz.entity.question.Question;
import by.bntu.fitr.isats.quiz.entity.user.Player;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LobbyDto {

    private Integer id;
    private Admin admin;
    private String password;
    private List<Player> players;
    private int playersAmountToStart;
    private int questionsAmount;
    private List<Question> questions;
    private int currentQuestionIndex;
    private GameStatus status;

}
