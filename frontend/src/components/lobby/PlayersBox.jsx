import React from "react";
import PropTypes from "prop-types";
import { Box } from "@material-ui/core";
import Player from "./Player";

const PlayersBox = props => {
  const { classes, players, playersAmountToStart } = props;
  const generatePlayers = () => {
    let generatedPlayers = [];
    for (let i = 0; i < players.length; i++) {
      generatedPlayers.push(<Player classes={classes} name={players[i].name} score={players[i].score} key={i + 1}/>);
    }
    for(let i = players.length; i < playersAmountToStart; i++) {
      generatedPlayers.push(<Player classes={classes} name="Не подключен" score="" key={i + 1}/>);
    }
    return generatedPlayers;
  };

  return <Box className={classes.playersBox}>{generatePlayers()}</Box>;
};

PlayersBox.propTypes = {
  classes: PropTypes.object.isRequired,
  playersAmountToStart: PropTypes.number.isRequired,
  players: PropTypes.array.isRequired
};

export default PlayersBox;
