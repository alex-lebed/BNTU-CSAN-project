import React from "react";
import { Switch, Route } from "react-router";
import Header from "./components/Header";
import Lobby from "./components/lobby/Lobby";
import { BrowserRouter } from "react-router-dom";
import { makeStyles } from "@material-ui/styles";

const useStyles = makeStyles({
  root: {
    height: "100vh",
    margin: "0px",
    padding: 0
  }
})

const App = () => {
  const classes = useStyles();
  return (
    <main className={classes.root}>
      <BrowserRouter>
        <Header />
        <Switch>
          <Route exact path="/lobby" component={Lobby} />
        </Switch>
      </BrowserRouter>
    </main>
  );
};

export default App;
