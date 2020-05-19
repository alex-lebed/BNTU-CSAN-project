package by.bntu.fitr.isats.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PlayerDto {

    private Integer id;
    private String name;
    private int score;
    private boolean winner;

}
