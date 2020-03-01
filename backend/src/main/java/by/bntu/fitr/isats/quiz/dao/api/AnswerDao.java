package by.bntu.fitr.isats.quiz.dao.api;

import by.bntu.fitr.isats.quiz.entity.question.Answer;
import java.util.List;

public interface AnswerDao {

    List<Answer> getByQuestionId(int id);

}
