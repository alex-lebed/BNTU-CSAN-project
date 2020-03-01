package by.bntu.fitr.isats.quiz.entity.question;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Question {

    private Integer id;
    private String text;
    private List<Answer> answers;

}
