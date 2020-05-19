package by.bntu.fitr.isats.quiz.service.impl;

import by.bntu.fitr.isats.quiz.dao.api.AnswerDao;
import by.bntu.fitr.isats.quiz.dao.api.QuestionDao;
import by.bntu.fitr.isats.quiz.entity.question.Question;
import by.bntu.fitr.isats.quiz.service.api.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao;
    private AnswerDao answerDao;

    @Autowired
    public QuestionServiceImpl(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    @Override
    public int getQuestionsAmount() {
        return questionDao.getQuestionsAmount();
    }

    @Override
    public List<Question> getRandomQuestions(int amount) {
        List<Question> questions = questionDao.getRandomQuestions(amount);
        questions.forEach(
                q -> q.setAnswers(answerDao.getByQuestionId(q.getId()))
        );
        return questions;
    }

}
