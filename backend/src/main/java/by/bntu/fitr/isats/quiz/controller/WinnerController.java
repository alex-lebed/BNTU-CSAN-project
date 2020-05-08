package by.bntu.fitr.isats.quiz.controller;

import by.bntu.fitr.isats.quiz.dto.WinnerDto;
import by.bntu.fitr.isats.quiz.exception.ServiceException;
import by.bntu.fitr.isats.quiz.service.api.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/winners")
public class WinnerController {

    private WinnerService service;

    @Autowired
    public WinnerController(WinnerService service) {
        this.service = service;
    }

    @GetMapping
    public List<WinnerDto> getWinners(@RequestParam int limit) throws ServiceException {
        if (limit < 0) {
            throw new ServiceException("Invalid limit: " + limit);
        }
        return service.getWinners(limit);
    }

}
