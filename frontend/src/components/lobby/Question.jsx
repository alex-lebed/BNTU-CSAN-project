import React from "react";
import PropTypes from "prop-types";
import { Box, Typography, Paper } from "@material-ui/core";

const Question = props => {
  const { classes } = props;

  return (
    <Box elevation={7} className={classes.questionsBox} component={Paper}>
      <Typography className={classes.question}>
        Самый старый факультет БНТУ?
      </Typography>
    </Box>
  );
};

Question.propTypes = {
  classes: PropTypes.array.isRequired
};

export default Question;
