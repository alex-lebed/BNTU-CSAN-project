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
  if (action.type === Actions.CONNECT_TO_LOBBY) {
    emit("event:connect", action.payload);
    return action.payload;
  } else if (action.type === Actions.CREATE_LOBBY) {
    return action.payload;
  } else if (action.type === Actions.UPDATE_LOBBY) {
    return action.payload;
  }
  return state;
};

export const leaders = (state = [], action) => {
  if (action.type === Actions.GET_LEADERS) {
    return action.payload;
  }
  return state;
};

export const currentQuestionAnswered = (state = false, action) => {
  if (action.type === Actions.SET_CURRENT_QUESTION_ANSWERED) {
    return action.payload;
  }
  return state;
};

export const currentPlayer = (state = null, action) => {
  if (action.type === Actions.SET_CURRENT_PLAYER) {
    return action.payload;
  }
  return state;
};

export const pressedAnswer = (state = null, action) => {
  if (action.type === Actions.SET_PRESSED_ANSWER) {
    return action.payload;
  }
  return state;
};

export default combineReducers({
  totalQuestionsAmount,
  lobby,
  leaders,
  currentQuestionAnswered,
  currentPlayer,
  pressedAnswer
});
