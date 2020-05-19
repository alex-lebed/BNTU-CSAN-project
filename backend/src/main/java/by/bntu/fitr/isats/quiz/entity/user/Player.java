package by.bntu.fitr.isats.quiz.entity.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Player {

    private Integer id;
    private String name;
    private int score;

    public void incrementScore(int amount) {
        score += amount;
    }


}
