package by.bntu.fitr.isats.quiz.dao.api;

import by.bntu.fitr.isats.quiz.entity.user.Admin;
import java.util.Optional;

public interface AdminDao {

    Optional<Admin> getByLoginAndPassword(String login, String password);

}
