package by.bntu.fitr.isats.quiz.service.api;

import by.bntu.fitr.isats.quiz.dto.LobbyConfigDto;
import by.bntu.fitr.isats.quiz.dto.LobbyDto;
import by.bntu.fitr.isats.quiz.dto.PlayerConnectDto;
import by.bntu.fitr.isats.quiz.exception.ServiceException;

public interface LobbyService {

    LobbyDto createLobby(LobbyConfigDto dto) throws ServiceException;
    LobbyDto connectPlayer(int lobbyId, PlayerConnectDto dto) throws ServiceException;
    LobbyDto proceed(int lobbyId, int playerId) throws ServiceException;

}
