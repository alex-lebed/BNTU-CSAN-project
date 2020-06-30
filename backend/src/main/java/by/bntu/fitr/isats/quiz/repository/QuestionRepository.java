package by.bntu.fitr.isats.quiz.repository;

import by.bntu.fitr.isats.quiz.entity.question.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query("FROM Question ORDER BY function('RAND')")
    List<Question> getRandomQuestions(Pageable pageable);

}
