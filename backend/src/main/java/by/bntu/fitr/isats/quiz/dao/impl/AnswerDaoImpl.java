package by.bntu.fitr.isats.quiz.dao.impl;

import by.bntu.fitr.isats.quiz.dao.api.AnswerDao;
import by.bntu.fitr.isats.quiz.entity.question.Answer;
import by.bntu.fitr.isats.quiz.utils.QueryFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AnswerDaoImpl implements AnswerDao {

    private static final String GET_BY_QUESTION_ID_QUERY = QueryFileReader.getQuery("get_answers_for_question.sql");

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Answer> mapper;

    @Autowired
    public AnswerDaoImpl(JdbcTemplate jdbcTemplate, RowMapper<Answer> mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public List<Answer> getByQuestionId(int id) {
        return jdbcTemplate.query(GET_BY_QUESTION_ID_QUERY, new Object[]{ id }, mapper);
    }

}
