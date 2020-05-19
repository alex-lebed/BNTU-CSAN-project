package by.bntu.fitr.isats.quiz.dto;

import by.bntu.fitr.isats.quiz.entity.game.GameStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class LobbyDto {

    private Integer id;
    private AdminDto admin;
    private String password;
    private List<PlayerDto> players;
    private int playersAmountToStart;
    private List<QuestionDto> questions;
    private int currentQuestionIndex;
    private GameStatus status;

}
