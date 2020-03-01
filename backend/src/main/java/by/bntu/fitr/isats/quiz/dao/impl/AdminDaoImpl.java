package by.bntu.fitr.isats.quiz.dao.impl;

import by.bntu.fitr.isats.quiz.dao.api.AdminDao;
import by.bntu.fitr.isats.quiz.entity.user.Admin;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class AdminDaoImpl implements AdminDao {

    @Override
    public Optional<Admin> getByLoginAndPassword(String login, String password) {
        return Optional.empty();
    }

}
