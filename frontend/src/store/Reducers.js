import Actions from "./Constants";
import { combineReducers } from "redux";
import { emit } from "../SocketClient";

export const totalQuestionsAmount = (state = 0, action) => {
  if (action.type === Actions.GET_TOTAL_QUESTIONS_AMOUNT) {
    return action.payload;
  }
  return state;
};

export const lobby = (state = null, action) => {
  if (
    action.type === Actions.CREATE_LOBBY ||
    action.type === Actions.CONNECT_TO_LOBBY
  ) {
    emit("event:connect", action.payload);
    return action.payload;
  } else if (action.type === Actions.UPDATE_LOBBY) {
    return action.payload;
  }
  return state;
};

export const winners = (state = [], action) => {
  if (action.type === Actions.GET_WINNERS) {
    return action.payload;
  }
  return state;
};

export const answerPossibility = (state = true, action) => {
  if (action.type === Actions.SET_ANSWER_POSSIBILITY) {
    return action.payload;
  }
  return state;
};

export default combineReducers({
  totalQuestionsAmount,
  lobby,
  winners,
  answerPossibility,
});
