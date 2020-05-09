import React, { useState } from "react";
import PropTypes from "prop-types";
import Box from "@material-ui/core/Box";
import Answer from "./Answer";

const AnswersBox = (props) => {
  const { classes, answers } = props;

  return (
    <Box className={classes.answersBox}>
      <table>
        <tbody>
          <tr>
            <td>
              <Answer classes={classes} answer={answers[0]} />
            </td>
            <td>
              <Answer classes={classes} answer={answers[1]} />
            </td>
          </tr>
          <tr>
            <td>
              <Answer classes={classes} answer={answers[2]} />
            </td>
            <td>
              <Answer classes={classes} answer={answers[3]} />
            </td>
          </tr>
        </tbody>
      </table>
    </Box>
  );
};

AnswersBox.propTypes = {
  classes: PropTypes.object.isRequired,
  answers: PropTypes.array.isRequired,
};

export default AnswersBox;
