import React from "react";
import PropTypes from "prop-types";
import PlayerIcon from "../../icons/player.png";
import { Box, Avatar, Typography } from "@material-ui/core";

const Player = (props) => {
  const { classes, name, score } = props;
  return (
    <Box elevation={3} className={classes.playerBox}>
      <Avatar className={classes.avatar} variant="rounded" src={PlayerIcon} />
      <Typography>{name}</Typography>
      <Typography>{score}</Typography>
    </Box>
  );
};

Player.propTypes = {
  classes: PropTypes.object.isRequired,
  name: PropTypes.string.isRequired,
  score: PropTypes.number.isRequired,
};

export default Player;
