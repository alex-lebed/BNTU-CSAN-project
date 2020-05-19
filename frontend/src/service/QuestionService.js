import axios from "axios";

const QUESTIONS_URL = "/questions";

export const QuestionService = {
  getTotalQuestionsAmount
};

function getTotalQuestionsAmount() {
  return axios.get(QUESTIONS_URL + "/amount")
}
