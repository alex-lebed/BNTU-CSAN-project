package by.bntu.fitr.isats.quiz.dao.api;

import by.bntu.fitr.isats.quiz.entity.user.Leader;
import java.util.List;

public interface LeaderDao {

    List<Leader> getLeaders(int limit);

}
