import Actions from "./Constants";
import { combineReducers } from "redux";

export const totalQuestionsAmount = (state = 0, action) => {
  if (action.type === Actions.GET_TOTAL_QUESTIONS_AMOUNT) {
    return action.payload;
  }
  return state;
};

export const lobby = (state = null, action) => {
  if (action.type === Actions.CREATE_LOBBY) {
    return action.payload;
  }
  return state;
};

export const winners = (state = [], action) => {
  if(action.type === Actions.GET_WINNERS) {
    return action.payload;
  }
  return state;
}

export default combineReducers({
  totalQuestionsAmount,
  lobby,
  winners
});
