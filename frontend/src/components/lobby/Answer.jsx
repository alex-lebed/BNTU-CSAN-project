import React from "react";
import PropTypes from "prop-types";
import { Button } from "@material-ui/core";

const Answers = props => {
  const { classes, text } = props;
  return (
    <Button variant="contained" className={classes.answer}>
      {text}
    </Button>
  );
};

Answers.propTypes = {
  classes: PropTypes.object.isRequired,
  text: PropTypes.string.isRequired
};

export default Answers;
