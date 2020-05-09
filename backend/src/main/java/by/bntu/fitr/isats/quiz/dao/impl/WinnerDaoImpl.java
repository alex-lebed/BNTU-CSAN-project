package by.bntu.fitr.isats.quiz.dao.impl;

import by.bntu.fitr.isats.quiz.dao.api.WinnerDao;
import by.bntu.fitr.isats.quiz.entity.user.Winner;
import by.bntu.fitr.isats.quiz.utils.QueryFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WinnerDaoImpl implements WinnerDao {

    private static final String SAVE_QUERY = QueryFileReader.getQuery("save_winner.sql");

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public WinnerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Winner winner) {
        Object[] params = {
                winner.getTimestamp(),
                winner.getPlayerName(),
                winner.getScore()
        };
        jdbcTemplate.update(SAVE_QUERY, params);
    }

}
