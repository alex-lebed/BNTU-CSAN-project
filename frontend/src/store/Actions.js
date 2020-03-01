import Actions from './Constants';
import { QuestionService } from "../service";

export const getTotalQuestionsAmount = () => {
    return dispatch => {
        QuestionService.getTotalQuestionsAmount().then(response => {
            return dispatch({
                type: Actions.GET_TOTAL_QUESTIONS_AMOUNT,
                payload: response.data
            })
        })
    }
}

