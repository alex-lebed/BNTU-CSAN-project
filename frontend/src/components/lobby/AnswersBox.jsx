import React from "react";
import PropTypes from "prop-types";
import Box from "@material-ui/core/Box";
import Answer from "./Answer";

const AnswersBox = props => {
  const { classes } = props;

  return (
    <Box className={classes.answersBox}>
      <table>
        <tbody>
          <tr>
            <td>
              <Answer
                classes={classes}
                text={"Информационных технологий и робототехники"}
              />
            </td>
            <td>
              <Answer classes={classes} text={"Автотракторный"} />
            </td>
          </tr>
          <tr>
            <td>
              <Answer classes={classes} text={"Строительный"} />
            </td>
            <td>
              <Answer classes={classes} text={"Энергетический"} />
            </td>
          </tr>
        </tbody>
      </table>
    </Box>
  );
};

AnswersBox.propTypes = {
  classes: PropTypes.array.isRequired
};

export default AnswersBox;
