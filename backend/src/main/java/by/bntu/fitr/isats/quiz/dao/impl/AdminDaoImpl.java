package by.bntu.fitr.isats.quiz.dao.impl;

import by.bntu.fitr.isats.quiz.dao.api.AdminDao;
import by.bntu.fitr.isats.quiz.entity.user.Admin;
import by.bntu.fitr.isats.quiz.utils.QueryFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class AdminDaoImpl extends AbstractDao<Admin> implements AdminDao {

    private static final String GET_ADMIN_QUERY = QueryFileReader.getQuery("get_admin.sql");

    @Autowired
    public AdminDaoImpl(JdbcTemplate jdbcTemplate, RowMapper<Admin> mapper) {
        super(jdbcTemplate, mapper);
    }

    @Override
    public Optional<Admin> getByLoginAndPassword(String login, String password) {
        return querySingleResult(GET_ADMIN_QUERY, login, password);
    }

}
