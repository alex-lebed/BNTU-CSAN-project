import React, { useState, useEffect } from "react";
import { Box, Paper, TextField, Typography, Button } from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";
import Colors from "../Colors";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { bindActionCreators } from "redux";
import { getTotalQuestionsAmount, createLobby } from "../store/Actions";
import { history } from "../helpers";

const useStyles = makeStyles({
  root: {
    width: "22%",
    minWidth: 250,
    height: 400,
    margin: "150px auto",
    position: "relative",
    fontFamily: '"Verdana"'
  },
  boxHeader: {
    width: "100%",
    height: "10%",
    backgroundColor: Colors.GREEN
  },
  input: {
    display: "block",
    marginTop: "20px",
    marginLeft: "10%",
    width: "80%"
  },
  button: {
    text: {
      fontWeight: 600
    },
    backgroundColor: Colors.GREEN,
    "&:hover": {
      backgroundColor: Colors.DARK_GREEN
    },
    width: "50%",
    position: "absolute",
    bottom: 15,
    right: "25%"
  }
});

const LobbyCreator = props => {
  const { totalQuestionsAmount, getTotalQuestionsAmount, createLobby } = props;
  const classes = useStyles();
  const [fields, setFields] = useState({
    playersAmount: "",
    questionsAmount: "",
    password: ""
  });
  const [errors, setErrors] = useState({
    playersAmount: false,
    questionsAmount: false,
    password: false
  });

  useEffect(() => {
    getTotalQuestionsAmount();
  });

  function handleChange(e) {
    const { name, value } = e.target;
    setFields(prev => ({
      ...prev,
      [name]: value
    }));
    if (value !== "") {
      validateField(name, value);
    } else {
      setErrors(prev => ({
        ...prev,
        [name]: true
      }));
    }
  }

  function validateField(name, value) {
    let valid = true;
    switch (name) {
      case "playersAmount": {
        if (value < 2 || value > 5) {
          valid = false;
        }
        break;
      }
      case "questionsAmount": {
        if (value < 1 || value > totalQuestionsAmount) {
          valid = false;
        }
        break;
      }
      default: {
        valid = true;
      }
    }
    setErrors(prev => ({
      ...prev,
      [name]: !valid
    }));
  }

  function submitForm() {
    if(errors.playersAmount || errors.questionsAmount || errors.password) return;
    if(fields.playersAmount === "" || fields.questionsAmount === "" || fields.password === "") return;
    createLobby({
      admin: { login: "admin", password: "admin", name: "admin"},
      password: fields.password,
      playersAmountToStart: fields.playersAmount,
      questionsAmount: fields.questionsAmount
    });
    history.push("/");
  }

  return (
    <Box elevation={7} className={classes.root} component={Paper}>
      <Box className={classes.boxHeader} component={Paper}>
        <Typography color="initial" variant="h6" align="center">
          Создание лобби
        </Typography>
      </Box>
      <TextField
        type="number"
        className={classes.input}
        required
        error={errors.playersAmount}
        variant="outlined"
        label="Количество игроков"
        name="playersAmount"
        onChange={handleChange}
        helperText={errors.playersAmount ? "Неверное кол-во игроков (2-5)" : ""}
        fullWidth
        size="small"
        value={fields.playersAmount}
      />
      <TextField
        type="number"
        className={classes.input}
        required
        error={errors.questionsAmount}
        variant="outlined"
        label="Количество вопросов"
        name="questionsAmount"
        onChange={handleChange}
        helperText={
          errors.questionsAmount
            ? "Неверное кол-во вопросов (1-" + totalQuestionsAmount + ")"
            : ""
        }
        fullWidth
        size="small"
        value={fields.questionsAmount}
      />
      <TextField
        type="password"
        className={classes.input}
        required
        variant="outlined"
        label="Пароль"
        name="password"
        error={errors.password}
        onChange={handleChange}
        fullWidth
        helperText={
          errors.password
            ? "Введите пароль"
            : ""
        }
        size="small"
        value={fields.password}
      />
      <Button
        className={classes.button}
        variant="contained"
        onClick={() => submitForm()}
      >
        Создать
      </Button>
    </Box>
  );
};

LobbyCreator.propTypes = {
  totalQuestionsAmount: PropTypes.number.isRequired,
  getTotalQuestionsAmount: PropTypes.func.isRequired,
  createLobby: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  totalQuestionsAmount: state.totalQuestionsAmount
});

const mapDispatchToProps = dispatch => {
  return bindActionCreators({ getTotalQuestionsAmount, createLobby }, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(LobbyCreator);
