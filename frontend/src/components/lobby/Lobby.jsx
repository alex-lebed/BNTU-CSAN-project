import React, { useEffect } from "react";
import Box from "@material-ui/core/Box";
import { makeStyles } from "@material-ui/styles";
import PlayersBox from "./PlayersBox";
import QuizBox from "./QuizBox";

const useStyles = makeStyles({
  root: {
    width: "50%",
    height: "100%",
    margin: "0 auto"
  },
  playersBox: {
    width: "100%",
    height: "30%",
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between"
  },
  quizBox: {
    width: "80%",
    height: "70%",
    maxHeight: "70%",
    paddingTop: 50,
    margin: "0 auto"
  },
  questionsBox: {
    height: "40%",
    margin: "0 auto",
    display: "flex",
    alignItems: "center",
    justifyContent: "center"
  },
  answersBox: {
    marginTop: "4%",
    height: "40%",
    "& table": {
      width: "80%",
      margin: "0 auto",
      height: "100%",
      tableLayout: "fixed"
    },
    "& td": {
      maxWidth: "50%",
      height: 100
    }
  },
  answer: {
    width: "90%",
    marginLeft: "10%",
    height: "83%",
    textTransform: "none"
  },
  question: {
    fontFamily: "Comic Sans MS",
    fontSize: 20
  },
  playerBox: {
    marginTop: 50,
    height: "60%",
    width: "15%",
    textAlign: "center"
  },
  avatar: {
    margin: "3% 20%",
    height: "60%",
    width: "60%"
  }
});

const Lobby = () => {
  const classes = useStyles();

  useEffect(() => {});

  return (
    <Box className={classes.root}>
      <PlayersBox classes={classes} />
      <QuizBox classes={classes} />
    </Box>
  );
};

export default Lobby;
