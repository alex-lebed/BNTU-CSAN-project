import React from "react";
import PropTypes from "prop-types";
import PlayerIcon from "../../icons/player.png";
import { Box, Avatar, Typography } from "@material-ui/core";

const Player = props => {
  const { classes, index } = props;
  return (
    <Box elevation={3} className={classes.playerBox}>
      <Avatar className={classes.avatar} variant="rounded" src={PlayerIcon} />
      <Typography>Player {index}</Typography>
      <Typography>0</Typography>
    </Box>
  );
};

Player.propTypes = {
  classes: PropTypes.array.isRequired,
  index: PropTypes.number.isRequired
};

export default Player;
