package by.bntu.fitr.isats.quiz.controller;

import by.bntu.fitr.isats.quiz.dto.LeaderDto;
import by.bntu.fitr.isats.quiz.exception.ServiceException;
import by.bntu.fitr.isats.quiz.service.api.LeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/leaders")
public class LeaderController {

    private LeaderService service;

    @Autowired
    public LeaderController(LeaderService service) {
        this.service = service;
    }

    @GetMapping
    public List<LeaderDto> getLeaders(@RequestParam int limit) throws ServiceException {
        if (limit < 0) {
            throw new ServiceException("Invalid limit: " + limit);
        }
        return service.getLeaders(limit);
    }

}
