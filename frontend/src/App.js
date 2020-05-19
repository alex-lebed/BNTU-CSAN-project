import React, { useEffect } from "react";
import { Router, Switch, Route } from "react-router";
import Header from "./components/Header";
import Lobby from "./components/lobby/Lobby";
import PropTypes from "prop-types";
import LobbyCreator from "./components/LobbyCreator";
import WinnersTable from "./components/LeadersTable";
import LobbyConnector from "./components/LobbyConnector";
import { makeStyles } from "@material-ui/styles";
import { history } from "./helpers";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import {
  updateLobby,
  setCurrentQuestionAnswered,
  setPressedAnswer
} from "./store/Actions";
import { socket } from "./SocketClient";

const useStyles = makeStyles({
  root: {
    height: "100vh",
    margin: "0px",
    padding: 0,
  },
});

const App = (props) => {
  const classes = useStyles();

  const { updateLobby, setCurrentQuestionAnswered, setPressedAnswer } = props;

  useEffect(() => {
    socket.on("event:connect", function (lobby) {
      setPressedAnswer(null);
      updateLobby(JSON.parse(lobby));
    });
    socket.on("event:answer", function (playerId) {
      setCurrentQuestionAnswered(true);
    });
    socket.on("event:nextQuestion", function (lobby) {
      updateLobby(JSON.parse(lobby));
      setPressedAnswer(null);
      setCurrentQuestionAnswered(false);
    });
  });

  return (
    <main className={classes.root}>
      <Router history={history}>
        <Header />
        <Switch>
          <Route exact path="/" component={LobbyConnector} />
          <Route exact path="/lobby" component={Lobby} />
          <Route exact path="/admin" component={LobbyCreator} />
          <Route exact path="/leaders" component={WinnersTable} />
        </Switch>
      </Router>
    </main>
  );
};

App.propTypes = {
  updateLobby: PropTypes.func.isRequired,
  setCurrentQuestionAnswered: PropTypes.func.isRequired,
  setPressedAnswer: PropTypes.func.isRequired
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    { updateLobby, setCurrentQuestionAnswered, setPressedAnswer },
    dispatch
  );
};

export default connect(null, mapDispatchToProps)(App);
