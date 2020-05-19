package by.bntu.fitr.isats.quiz.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<T> mapper;

    protected AbstractDao(JdbcTemplate jdbcTemplate, RowMapper<T> mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected List<T> queryList(String query, Object... params) {
        return jdbcTemplate.query(query, params, mapper);
    }

    protected Optional<T> querySingleResult(String query, Object... params) {
        List<T> list = jdbcTemplate.query(query ,params, mapper);
        return (list.size() == 1) ? Optional.of(list.get(0)) : Optional.empty();
    }

}
