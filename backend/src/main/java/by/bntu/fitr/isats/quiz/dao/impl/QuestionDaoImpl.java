package by.bntu.fitr.isats.quiz.dao.impl;

import by.bntu.fitr.isats.quiz.dao.api.QuestionDao;
import by.bntu.fitr.isats.quiz.entity.question.Question;
import by.bntu.fitr.isats.quiz.utils.QueryFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    private String GET_QUESTIONS_AMOUNT_QUERY = QueryFileReader.getQuery("get_questions_amount.sql");
    private String GET_RANDOM_QUESTIONS_QUERY = QueryFileReader.getQuery("get_random_questions.sql");

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Question> mapper;

    @Autowired
    public QuestionDaoImpl(JdbcTemplate jdbcTemplate, RowMapper<Question> mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public int getQuestionsAmount() {
        // queryForObject() does not throw NPE
        return jdbcTemplate.queryForObject(GET_QUESTIONS_AMOUNT_QUERY, Integer.class);
    }

    @Override
    public List<Question> getRandomQuestions(int amount) {
        return jdbcTemplate.query(GET_RANDOM_QUESTIONS_QUERY, new Object[]{amount}, mapper);
    }

}
