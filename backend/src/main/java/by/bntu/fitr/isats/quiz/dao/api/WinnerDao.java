package by.bntu.fitr.isats.quiz.dao.api;

import by.bntu.fitr.isats.quiz.entity.user.Winner;
import java.util.List;

public interface WinnerDao {

    List<Winner> getWinners(int limit);

}
