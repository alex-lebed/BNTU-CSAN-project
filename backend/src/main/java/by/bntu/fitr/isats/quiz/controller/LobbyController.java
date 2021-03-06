package by.bntu.fitr.isats.quiz.controller;

import by.bntu.fitr.isats.quiz.dto.LobbyConfigDto;
import by.bntu.fitr.isats.quiz.dto.LobbyDto;
import by.bntu.fitr.isats.quiz.dto.PlayerConnectDto;
import by.bntu.fitr.isats.quiz.exception.ServiceException;
import by.bntu.fitr.isats.quiz.service.api.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/lobbies")
public class LobbyController {

    private LobbyService service;

    @Autowired
    public LobbyController(LobbyService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LobbyDto createLobby(@Valid @RequestBody LobbyConfigDto dto) throws ServiceException {
        return service.createLobby(dto);
    }

    @PatchMapping("/{id}/connect")
    @ResponseStatus(HttpStatus.OK)
    public LobbyDto connectPlayer(@PathVariable("id") int lobbyId, @Valid @RequestBody PlayerConnectDto dto) throws ServiceException {
        return service.connectPlayer(lobbyId, dto);
    }

    @PatchMapping("/{id}/proceed")
    @ResponseStatus(HttpStatus.OK)
    public LobbyDto proceed(@PathVariable("id") int lobbyId, @RequestParam int playerId) throws ServiceException {
        return service.proceed(lobbyId, playerId);
    }


}
