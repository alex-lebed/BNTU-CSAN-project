package by.bntu.fitr.isats.quiz.mapper;

import by.bntu.fitr.isats.quiz.entity.question.Answer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AnswerRowMapper implements RowMapper<Answer> {

    private static final String ID = "id";
    private static final String TEXT = "answer_text";
    private static final String CORRECT = "is_correct";

    @Override
    public Answer mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(ID);
        String text = resultSet.getString(TEXT);
        boolean correct = resultSet.getBoolean(CORRECT);
        return new Answer(id, text, correct);
    }

}
