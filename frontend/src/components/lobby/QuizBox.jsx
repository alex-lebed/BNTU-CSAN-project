import React from "react";
import PropTypes from "prop-types";
import Box from "@material-ui/core/Box";
import Question from "./Question";
import AnswersBox from "./AnswersBox";

const QuizBox = props => {
  const { classes, question, answers } = props;

  return (
    <Box className={classes.quizBox}>
      <Question classes={classes} question={question}/>
      <AnswersBox classes={classes} answers={answers}/>
    </Box>
  );
};

QuizBox.propTypes = {
  classes: PropTypes.object.isRequired,
  question: PropTypes.object.isRequired,
  answers: PropTypes.array.isRequired
};

export default QuizBox;
