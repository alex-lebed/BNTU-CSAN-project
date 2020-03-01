import React from "react";
import PropTypes from "prop-types";
import Box from "@material-ui/core/Box";
import Question from "./Question";
import AnswersBox from "./AnswersBox";

const QuizBox = props => {
  const { classes } = props;

  return (
    <Box className={classes.quizBox}>
      <Question classes={classes} />
      <AnswersBox classes={classes} />
    </Box>
  );
};

QuizBox.propTypes = {
  classes: PropTypes.array.isRequired
};

export default QuizBox;
