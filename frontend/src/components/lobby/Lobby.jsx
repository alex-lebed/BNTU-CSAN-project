import React from "react";
import { connect } from "react-redux";
import { Box, Typography, Paper } from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";
import PlayersBox from "./PlayersBox";
import QuizBox from "./QuizBox";
import Colors from "../../Colors";

const useStyles = makeStyles({
  root: {
    width: "50%",
    minWidth: 500,
    height: "100%",
    margin: "0 auto",
  },
  nullLobbyRoot: {
    width: "50%",
    height: "100%",
    margin: "0 auto",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  },
  playersBox: {
    width: "100%",
    height: "30%",
    display: "flex",
    justifyContent: "space-between",
  },
  quizBox: {
    width: "80%",
    height: "70%",
    maxHeight: "70%",
    paddingTop: 50,
    margin: "0 auto",
  },
  questionsBox: {
    height: "40%",
    margin: "0 auto",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  },
  answersBox: {
    marginTop: "4%",
    height: "40%",
    "& table": {
      width: "80%",
      margin: "0 auto",
      height: "100%",
      tableLayout: "fixed",
    },
    "& td": {
      maxWidth: "50%",
      height: 100,
    },
  },
  question: {
    fontFamily: "Comic Sans MS",
    fontSize: 20,
  },
  answer: {
    width: "90%",
    marginLeft: "10%",
    height: "83%",
    textTransform: "none",
  },
  buttonRed: {
    backgroundColor: Colors.RED,
  },
  buttonGreen: {
    backgroundColor: Colors.GREEN,
  },
  buttonGrey: {
    backgroundColor: Colors.GREY,
  },
  playerBox: {
    marginTop: 60,
    paddingBottom: 10,
    height: "80%",
    width: "20%",
    minWidth: 100,
    minHeight: 170,
    textAlign: "center",
  },
  winnerBorder: {
    border: "4px solid " + Colors.GREEN
  },
  avatar: {
    margin: "3% 15%",
    height: "70%",
    width: "70%"
  },
  nullOrWaitLobby: {
    padding: 20,
    fontFamily: "Comic Sans MS",
  },
  waitingPlayersBox: {
    width: "50%",
    margin: "20% auto",
  },
});

const Lobby = (props) => {
  const classes = useStyles();
  const { lobby } = props;

  if (lobby === null) {
    return (
      <Box className={classes.nullLobbyRoot}>
        <Box elevation={7} component={Paper}>
          <Typography
            className={classes.nullOrWaitLobby}
            variant="h6"
            align="center"
          >
            Лобби еще не создано!
          </Typography>
        </Box>
      </Box>
    );
  } else if (lobby.status === "WAITING_PLAYERS") {
    return (
      <Box className={classes.root}>
        <PlayersBox
          classes={classes}
          playersAmountToStart={lobby.playersAmountToStart}
          players={lobby.players}
        />
        <Box
          className={classes.waitingPlayersBox}
          elevation={7}
          component={Paper}
        >
          <Typography
            className={classes.nullOrWaitLobby}
            variant="h6"
            align="center"
          >
            Ожидание игроков...
          </Typography>
        </Box>
      </Box>
    );
  } else if (lobby.status === "STARTED") {
    return (
      <Box className={classes.root}>
        <PlayersBox
          classes={classes}
          playersAmountToStart={lobby.playersAmountToStart}
          players={lobby.players}
        />
        <QuizBox
          classes={classes}
          question={lobby.questions[lobby.currentQuestionIndex]}
          answers={lobby.questions[lobby.currentQuestionIndex].answers}
        />
      </Box>
    );
  } else {
    return (
      <Box className={classes.root}>
        <PlayersBox
          classes={classes}
          playersAmountToStart={lobby.playersAmountToStart}
          players={lobby.players}
        />
        <Box
          className={classes.waitingPlayersBox}
          elevation={7}
          component={Paper}
        >
          <Typography
            className={classes.nullOrWaitLobby}
            variant="h6"
            align="center"
          >
            Игра окончена
          </Typography>
        </Box>
      </Box>
    );
  }
};

const mapStateToProps = (state) => ({
  lobby: state.lobby,
});

export default connect(mapStateToProps, null)(Lobby);
