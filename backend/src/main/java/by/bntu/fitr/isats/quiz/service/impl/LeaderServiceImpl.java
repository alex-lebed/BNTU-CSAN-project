package by.bntu.fitr.isats.quiz.service.impl;

import by.bntu.fitr.isats.quiz.dao.api.LeaderDao;
import by.bntu.fitr.isats.quiz.dto.LeaderDto;
import by.bntu.fitr.isats.quiz.service.api.LeaderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderServiceImpl implements LeaderService {

    private ModelMapper mapper;
    private LeaderDao dao;

    @Autowired
    public LeaderServiceImpl(ModelMapper mapper, LeaderDao dao) {
        this.mapper = mapper;
        this.dao = dao;
    }

    @Override
    public List<LeaderDto> getLeaders(int limit) {
        return dao.getLeaders(limit)
                .stream()
                .map(w -> mapper.map(w, LeaderDto.class))
                .collect(Collectors.toList());
    }

}
