package by.bntu.fitr.isats.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WinnerDto {

    private String name;
    private int gamesWon;
    private int score;

}
