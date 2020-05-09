package by.bntu.fitr.isats.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class QuestionDto {

    private String text;
    private List<AnswerDto> answers;

}
