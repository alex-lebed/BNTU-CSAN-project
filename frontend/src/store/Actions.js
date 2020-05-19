import Actions from "./Constants";
import { QuestionService, LeaderService } from "../service";
import {
  connectToLobby as connectToLobbyService,
  createLobby as createLobbyService,
} from "../service/LobbyService";

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
      dispatch({
        type: Actions.CONNECT_TO_LOBBY,
        payload: response.data,
      });
      dispatch(setCurrentPlayer(response.data.players[response.data.players.length - 1]));
      dispatch(setCurrentQuestionAnswered(false));
    });
  };
};

export const updateLobby = (lobby) => {
  return {
    type: Actions.UPDATE_LOBBY,
    payload: lobby,
  };
};

export const getLeaders = () => {
  return (dispatch) => {
    LeaderService.getLeaders().then((response) => {
      return dispatch({
        type: Actions.GET_LEADERS,
        payload: response.data,
      });
    });
  };
};

export const setCurrentQuestionAnswered = (value) => {
  return (dispatch) => {
    dispatch({
      type: Actions.SET_CURRENT_QUESTION_ANSWERED,
      payload: value,
    });
  };
};

export const setCurrentPlayer = (player) => {
  return (dispatch) => {
    dispatch({
      type: Actions.SET_CURRENT_PLAYER,
      payload: player,
    });
  };
};

export const setPressedAnswer = (answer) => {
  return (dispatch) => {
    dispatch({
      type: Actions.SET_PRESSED_ANSWER,
      payload: answer,
    });
  };
}