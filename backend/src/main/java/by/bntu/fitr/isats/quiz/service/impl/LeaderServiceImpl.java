package by.bntu.fitr.isats.quiz.service.impl;

import by.bntu.fitr.isats.quiz.dto.LeaderDto;
import by.bntu.fitr.isats.quiz.repository.WinnerRepository;
import by.bntu.fitr.isats.quiz.service.api.LeaderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderServiceImpl implements LeaderService {

    private ModelMapper mapper;
    private WinnerRepository repository;

    @Autowired
    public LeaderServiceImpl(ModelMapper mapper, WinnerRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<LeaderDto> getLeaders(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return repository.getLeaders(pageable)
                .stream()
                .map(l -> mapper.map(l, LeaderDto.class))
                .collect(Collectors.toList());
    }

}
