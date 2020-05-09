import React, { useState } from "react";
import PropTypes from "prop-types";
import { Button } from "@material-ui/core";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { setAnswerPossibility } from "../../store/Actions";

const Answer = (props) => {
  const { classes, answer, answerPossibility, setAnswerPossibility } = props;
  const [correct, setCorrect] = useState(undefined);

  function checkAnswer() {
    if (!answerPossibility) {
      return;
    }
    setCorrect(answer.correct);
    setAnswerPossibility(false);
  }

  function getColorByCorrectness() {
    if(correct === undefined) {
      return classes.buttonGrey;
    } else if(correct) {
      return classes.buttonGreen;
    } else {
      return classes.buttonRed;
    }
  }

  return (
    <Button
      variant="contained"
      className={`${classes.answer} ${getColorByCorrectness()}`}
      onClick={() => checkAnswer(answer)}
    >
      {answer.text}
    </Button>
  );
};

Answer.propTypes = {
  classes: PropTypes.array.isRequired,
  answer: PropTypes.object.isRequired,
  answerPossibility: PropTypes.bool.isRequired,
  setAnswerPossibility: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  answerPossibility: state.answerPossibility,
});

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators({ setAnswerPossibility }, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(Answer);
