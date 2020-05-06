import Actions from "./Constants";
import { QuestionService, LobbyService, PlayerService } from "../service";

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
    LobbyService.createLobby(lobby).then((response) => {
      return dispatch({
        type: Actions.CREATE_LOBBY,
        payload: response.data,
      });
    });
  };
};

export const getCurrentLobby = () => {
  return (dispatch) => {
    LobbyService.getCurrentLobby().then((response) => {
      return dispatch({
        type: Actions.GET_CURRENT_LOBBY,
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
}
