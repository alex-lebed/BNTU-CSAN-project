package by.bntu.fitr.isats.quiz.mapper;

import by.bntu.fitr.isats.quiz.entity.user.Admin;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AdminRowMapper implements RowMapper<Admin> {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "admin_password";
    private static final String NAME = "admin_name";

    @Override
    public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
        String login = resultSet.getString(LOGIN);
        String password = resultSet.getString(PASSWORD);
        String name = resultSet.getString(NAME);
        return Admin.builder()
                .login(login)
                .password(password)
                .name(name)
                .build();
    }

}
