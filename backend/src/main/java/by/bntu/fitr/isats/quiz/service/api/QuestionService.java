package by.bntu.fitr.isats.quiz.service.api;

import by.bntu.fitr.isats.quiz.entity.question.Question;
import java.util.List;

public interface QuestionService {

    int getQuestionsAmount();
    List<Question> getRandomQuestions(int amount);

}
