import React from "react";
import PropTypes from "prop-types";
import { Box } from "@material-ui/core";
import Player from "./Player";

const PlayersBox = props => {
  const { classes } = props;
  const generatePlayers = () => {
    let players = [];
    for (var i = 0; i < 5; i++) {
      players.push(<Player classes={classes} index={i + 1} />);
    }
    return players;
  };

  return <Box className={classes.playersBox}>{generatePlayers()}</Box>;
};

PlayersBox.propTypes = {
  classes: PropTypes.array.isRequired
};

export default PlayersBox;
