import React from "react";
import PropTypes from "prop-types";
import { Box, Typography, Paper } from "@material-ui/core";

const Question = (props) => {
  const { classes, question } = props;

  return (
    <Box elevation={7} className={classes.questionsBox} component={Paper}>
      <Typography className={classes.question} align="center">
        {question.text}
      </Typography>
    </Box>
  );
};

Question.propTypes = {
  classes: PropTypes.object.isRequired,
  question: PropTypes.object.isRequired,
};

export default Question;
