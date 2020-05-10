import React from "react";
import PropTypes from "prop-types";
import { Button } from "@material-ui/core";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import {
  setCurrentQuestionAnswered,
  setPressedAnswer,
} from "../../store/Actions";
import { emit } from "../../SocketClient";

const Answer = (props) => {
  const {
    classes,
    answer,
    currentQuestionAnswered,
    setCurrentQuestionAnswered,
    currentPlayer,
    pressedAnswer,
    setPressedAnswer,
  } = props;

  function checkAnswer() {
    if (currentQuestionAnswered) {
      return;
    }
    setPressedAnswer(answer);
    setCurrentQuestionAnswered(true);
    if (answer.correct) {
      emit("event:answer", currentPlayer.id);
    }
  }

  function getColorByCorrectness() {
    if (!currentQuestionAnswered) {
      return classes.buttonGrey;
    } else if (pressedAnswer === answer) {
      return answer.correct ? classes.buttonGreen : classes.buttonRed;
    } else if (currentQuestionAnswered && answer.correct) {
      return classes.buttonGreen;
    }
  }

  return (
    <Button
      variant="contained"
      className={`${classes.answer} ${getColorByCorrectness()}`}
      onClick={() => checkAnswer()}
    >
      {answer.text}
    </Button>
  );
};

Answer.propTypes = {
  classes: PropTypes.object.isRequired,
  answer: PropTypes.object.isRequired,
  currentQuestionAnswered: PropTypes.bool.isRequired,
  setCurrentQuestionAnswered: PropTypes.func.isRequired,
  pressedAnswer: PropTypes.object.isRequired,
  setPressedAnswer: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  currentQuestionAnswered: state.currentQuestionAnswered,
  currentPlayer: state.currentPlayer,
  pressedAnswer: state.pressedAnswer,
});

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    { setCurrentQuestionAnswered, setPressedAnswer },
    dispatch
  );
};

export default connect(mapStateToProps, mapDispatchToProps)(Answer);
