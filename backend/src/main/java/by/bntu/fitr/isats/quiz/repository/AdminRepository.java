package by.bntu.fitr.isats.quiz.repository;

import by.bntu.fitr.isats.quiz.entity.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    Optional<Admin> findByLoginAndPassword(String login, String password);

}
