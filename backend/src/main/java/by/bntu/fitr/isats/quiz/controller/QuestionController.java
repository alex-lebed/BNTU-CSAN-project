package by.bntu.fitr.isats.quiz.controller;

import by.bntu.fitr.isats.quiz.dto.QuestionDto;
import by.bntu.fitr.isats.quiz.service.api.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService service;

    @Autowired
    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/amount")
    public int getQuestionsAmount() {
        return service.getQuestionsAmount();
    }

}
