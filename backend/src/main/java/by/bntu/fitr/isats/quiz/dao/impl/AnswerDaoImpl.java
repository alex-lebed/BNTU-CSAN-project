package by.bntu.fitr.isats.quiz.dao.impl;

import by.bntu.fitr.isats.quiz.dao.api.AnswerDao;
import by.bntu.fitr.isats.quiz.entity.question.Answer;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AnswerDaoImpl implements AnswerDao {

    @Override
    public List<Answer> getByQuestionId(int id) {
        return null;
    }

}
