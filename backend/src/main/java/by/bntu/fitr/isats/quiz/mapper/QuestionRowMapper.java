package by.bntu.fitr.isats.quiz.mapper;

import by.bntu.fitr.isats.quiz.entity.question.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

}
