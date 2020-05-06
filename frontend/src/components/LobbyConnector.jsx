import React, { useState, useEffect } from "react";
import { Box, Paper, TextField, Typography, Button } from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";
import Colors from "../Colors";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { bindActionCreators } from "redux";
import { connectToLobby } from "../store/Actions";
import { history } from "../helpers";

const useStyles = makeStyles({
  root: {
    width: "22%",
    height: 350,
    margin: "150px auto",
    position: "relative",
    fontFamily: '"Verdana"',
  },
  boxHeader: {
    width: "100%",
    height: "15%",
    backgroundColor: Colors.GREEN,
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  },
  input: {
    display: "block",
    marginTop: "20px",
    marginLeft: "10%",
    width: "80%",
  },
  button: {
    text: {
      fontWeight: 600,
    },
    backgroundColor: Colors.GREEN,
    "&:hover": {
      backgroundColor: Colors.DARK_GREEN,
    },
    width: "50%",
    position: "absolute",
    bottom: 15,
    right: "25%",
  },
});

const LobbyConnector = (props) => {
  const classes = useStyles();
  const [fields, setFields] = useState({
    name: "",
    password: "",
  });
  const [errors, setErrors] = useState({
    name: false,
    password: false,
    connection: false,
  });

  useEffect(() => {});

  function handleChange(e) {
    const { name, value } = e.target;
    setFields((prev) => ({
      ...prev,
      [name]: value,
    }));
    if (value === "") {
      setErrors((prev) => ({
        ...prev,
        [name]: true,
      }));
    }
  }

  function submitForm() {
    if (errors.name || errors.password) return;
    if (fields.name === "" || fields.password === "") return;
    props.connectToLobby(fields.name, fields.password);
    history.push("/lobby");
  }

  return (
    <Box elevation={7} className={classes.root} component={Paper}>
      <Box className={classes.boxHeader} component={Paper}>
        <Typography color="initial" variant="h6">
          Подключение к лобби
        </Typography>
      </Box>
      <TextField
        type="text"
        className={classes.input}
        required
        error={errors.name}
        variant="outlined"
        label="Имя"
        name="name"
        onChange={handleChange}
        helperText={errors.name ? "Введите имя" : ""}
        fullWidth
        size="small"
        value={fields.name}
      />
      <TextField
        type="password"
        className={classes.input}
        required
        error={errors.password}
        variant="outlined"
        label="Пароль"
        name="password"
        onChange={handleChange}
        fullWidth
        size="small"
        helperText={errors.password ? "Введите пароль" : ""}
        value={fields.password}
      />
      {errors.connection ? (
        <Typography color="initial" variant="h6">
          Лобби с таким паролем не существует!
        </Typography>
      ) : (
        function () {}
      )}
      <Button
        className={classes.button}
        variant="contained"
        onClick={() => submitForm()}
      >
        Подключиться
      </Button>
    </Box>
  );
};

LobbyConnector.propTypes = {
  connectToLobby: PropTypes.func.isRequired,
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators({ connectToLobby }, dispatch);
};

export default connect(null, mapDispatchToProps)(LobbyConnector);
