package by.bntu.fitr.isats.quiz.service.impl;

import by.bntu.fitr.isats.quiz.repository.QuestionRepository;
import by.bntu.fitr.isats.quiz.service.api.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository repository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public int getQuestionsAmount() {
        return (int) repository.count();
    }


}
