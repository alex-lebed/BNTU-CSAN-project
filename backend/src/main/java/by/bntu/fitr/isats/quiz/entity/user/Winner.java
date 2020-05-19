package by.bntu.fitr.isats.quiz.entity.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Winner {

    private Integer id;
    private String playerName;
    private Timestamp timestamp;
    private int score;

}
