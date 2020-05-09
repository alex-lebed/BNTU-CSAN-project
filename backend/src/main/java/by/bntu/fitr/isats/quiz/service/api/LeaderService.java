package by.bntu.fitr.isats.quiz.service.api;

import by.bntu.fitr.isats.quiz.dto.LeaderDto;
import java.util.List;

public interface LeaderService {

    List<LeaderDto> getLeaders(int limit);

}
