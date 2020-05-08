package by.bntu.fitr.isats.quiz.dao.impl;

import by.bntu.fitr.isats.quiz.dao.api.WinnerDao;
import by.bntu.fitr.isats.quiz.entity.user.Winner;
import by.bntu.fitr.isats.quiz.utils.QueryFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class WinnerDaoImpl extends AbstractDao<Winner> implements WinnerDao {

    private static final String GET_WINNERS_QUERY = QueryFileReader.getQuery("get_winners.sql");

    @Autowired
    public WinnerDaoImpl(JdbcTemplate jdbcTemplate, RowMapper<Winner> mapper) {
        super(jdbcTemplate, mapper);
    }

    @Override
    public List<Winner> getWinners(int limit) {
        return queryList(GET_WINNERS_QUERY, limit);
    }

}
