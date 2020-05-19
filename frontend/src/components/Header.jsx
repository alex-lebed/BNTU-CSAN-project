import React from "react";
import { AppBar, Toolbar, Typography, Link, Box } from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";
import Colors from "../Colors";
import { history } from "../helpers";

const useStyles = makeStyles({
  appBar: {
    backgroundColor: Colors.GREEN,
    height: 60,
    position: "fixed",
    width: "100%",
    top: 0,
  },
  logo: {
    marginLeft: 5
  },
  linksContainer: {
    width: "25%",
    minWidth: 375,
    position: "absolute",
    right: 0,
    display: "flex",
    justifyContent: "space-between"
  },
  link: {
    textDecoration: "none",
    color: Colors.WHITE,
    fontWeight: 400,
    fontSize: 20
  },
  lastLink: {
    marginRight: 40
  }
});

const Header = () => {
  const classes = useStyles();
  return (
    <AppBar className={classes.appBar} fixed>
      <Toolbar>
        <Typography className={classes.logo} variant="h6" color="initial">
          BNTU Quiz
        </Typography>
        <Box className={classes.linksContainer}>
          <Link
            className={classes.link}
            href=""
            onClick={() => history.push("/")}
          >
            Главная
          </Link>
          <Link
            className={classes.link}
            href=""
            onClick={() => history.push("/lobby")}
          >
            Лобби
          </Link>
          <Link
            className={`${classes.link} ${classes.lastLink}`}
            href=""
            onClick={() => history.push("/leaders")}
          >
            Таблица лидеров
          </Link>
        </Box>
      </Toolbar>
    </AppBar>
  );
};

export default Header;
