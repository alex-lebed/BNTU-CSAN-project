package by.bntu.fitr.isats.quiz.dao.impl;

import by.bntu.fitr.isats.quiz.dao.api.LeaderDao;
import by.bntu.fitr.isats.quiz.entity.user.Leader;
import by.bntu.fitr.isats.quiz.utils.QueryFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class LeaderDaoImpl extends AbstractDao<Leader> implements LeaderDao {

    private static final String GET_WINNERS_QUERY = QueryFileReader.getQuery("get_leaders.sql");

    @Autowired
    public LeaderDaoImpl(JdbcTemplate jdbcTemplate, RowMapper<Leader> mapper) {
        super(jdbcTemplate, mapper);
    }

    @Override
    public List<Leader> getLeaders(int limit) {
        return queryList(GET_WINNERS_QUERY, limit);
    }

}
