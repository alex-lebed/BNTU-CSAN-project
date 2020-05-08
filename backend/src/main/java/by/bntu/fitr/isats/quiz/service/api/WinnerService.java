package by.bntu.fitr.isats.quiz.service.api;

import by.bntu.fitr.isats.quiz.dto.WinnerDto;
import java.util.List;

public interface WinnerService {

    List<WinnerDto> getWinners(int limit);

}
