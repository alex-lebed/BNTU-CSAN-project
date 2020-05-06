import Actions from "./Constants";
import { QuestionService, PlayerService } from "../service";
import { connectToLobby as connectToLobbyService, createLobby as createLobbyService} from "../service/LobbyService";

export const getTotalQuestionsAmount = () => {
  return (dispatch) => {
    QuestionService.getTotalQuestionsAmount().then((response) => {
      return dispatch({
        type: Actions.GET_TOTAL_QUESTIONS_AMOUNT,
        payload: response.data,
      });
    });
  };
};

export const createLobby = (lobby) => {
  return (dispatch) => {
    createLobbyService(lobby).then((response) => {
      return dispatch({
        type: Actions.CREATE_LOBBY,
        payload: response.data,
      });
    });
  };
};

export const connectToLobby = (username, lobbyPassword) => {
  return (dispatch) => {
    connectToLobbyService(username, lobbyPassword).then((response) => {
      return dispatch({
        type: Actions.CONNECT_TO_LOBBY,
        payload: response.data,
      });
    });
  };
};

export const getWinners = () => {
  return (dispatch) => {
    PlayerService.getWinners().then((response) => {
      return dispatch({
        type: Actions.GET_WINNERS,
        payload: response.data,
      });
    });
  };
};
