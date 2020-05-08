package by.bntu.fitr.isats.quiz.mapper;

import by.bntu.fitr.isats.quiz.entity.user.Winner;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class WinnerRowMapper implements RowMapper<Winner> {

    private static final String NAME = "player_name";
    private static final String GAMES_WON = "games_won";
    private static final String SCORE = "score_sum";

    @Override
    public Winner mapRow(ResultSet resultSet, int i) throws SQLException {
        String name = resultSet.getString(NAME);
        int gamesWon = resultSet.getInt(GAMES_WON);
        int score = resultSet.getInt(SCORE);
        return Winner.builder()
                .name(name)
                .gamesWon(gamesWon)
                .score(score)
                .build();
    }

}
