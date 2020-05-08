package by.bntu.fitr.isats.quiz.service.impl;

import by.bntu.fitr.isats.quiz.dao.api.WinnerDao;
import by.bntu.fitr.isats.quiz.dto.WinnerDto;
import by.bntu.fitr.isats.quiz.service.api.WinnerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WinnerServiceImpl implements WinnerService {

    private ModelMapper mapper;
    private WinnerDao dao;

    @Autowired
    public WinnerServiceImpl(ModelMapper mapper, WinnerDao dao) {
        this.mapper = mapper;
        this.dao = dao;
    }

    @Override
    public List<WinnerDto> getWinners(int limit) {
        return dao.getWinners(limit)
                .stream()
                .map(w -> mapper.map(w, WinnerDto.class))
                .collect(Collectors.toList());
    }

}
