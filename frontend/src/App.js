import React from "react";
import { Router, Switch, Route } from "react-router";
import Header from "./components/Header";
import Lobby from "./components/lobby/Lobby";
import LobbyCreator from "./components/LobbyCreator";
import WinnersTable from "./components/WinnersTable";
import LobbyConnector from "./components/LobbyConnector";
import { makeStyles } from "@material-ui/styles";
import { history } from "./helpers";

const useStyles = makeStyles({
  root: {
    height: "100vh",
    margin: "0px",
    padding: 0
  }
});

const App = () => {
  const classes = useStyles();
  return (
    <main className={classes.root}>
      <Router history={history}>
        <Header />
        <Switch>
          <Route exact path="/" component={LobbyConnector}/>
          <Route exact path="/lobby" component={Lobby} />
          <Route exact path="/admin" component={LobbyCreator} />
          <Route exact path="/winners" component={WinnersTable} />
        </Switch>
      </Router>
    </main>
  );
};

export default App;
