package by.bntu.fitr.isats.quiz.dao.api;

import by.bntu.fitr.isats.quiz.entity.question.Question;
import java.util.List;

public interface QuestionDao {

    int getQuestionsAmount();
    List<Question> getRandomQuestions(int amount);

}
