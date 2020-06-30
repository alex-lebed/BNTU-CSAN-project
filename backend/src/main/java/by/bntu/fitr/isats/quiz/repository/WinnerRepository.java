package by.bntu.fitr.isats.quiz.repository;

import by.bntu.fitr.isats.quiz.entity.user.Leader;
import by.bntu.fitr.isats.quiz.entity.user.Winner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, Integer> {

    @Query("SELECT " +
            "    new by.bntu.fitr.isats.quiz.entity.user.Leader(w.playerName, CAST(COUNT(w) AS integer), CAST(SUM(w.score) AS integer)) " +
            "FROM " +
            "    Winner AS w " +
            "GROUP BY " +
            "    w.playerName " +
            "ORDER BY " +
            "    COUNT(w) DESC, " +       // games won
            "    SUM(w.score) DESC, " +   // total score
            "    w.playerName ASC")      // name
    List<Leader> getLeaders(Pageable pageable);

}
