package by.bntu.fitr.isats.quiz.service.impl;

import by.bntu.fitr.isats.quiz.dao.api.QuestionDao;
import by.bntu.fitr.isats.quiz.entity.question.Question;
import by.bntu.fitr.isats.quiz.service.api.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao;

    @Autowired
    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public int getQuestionsAmount() {
        return questionDao.getQuestionsAmount();
    }

    @Override
    public List<Question> getRandomQuestions(int amount) {
        return questionDao.getRandomQuestions(amount);
    }

}
