import React from "react";
import { AppBar, Toolbar, Typography } from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";
import Colors from "../Colors";

const useStyles = makeStyles({
  appBar: {
    backgroundColor: Colors.GREEN,
    height: 60
  }
});

const Header = () => {
  const classes = useStyles();
  return (
    <AppBar className={classes.appBar} position="fixed">
      <Toolbar>
        <Typography variant="h6" color="initial">
          BNTU Quiz
        </Typography>
      </Toolbar>
    </AppBar>
  );
};

export default Header;
