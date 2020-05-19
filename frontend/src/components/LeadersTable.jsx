import React, { useEffect } from "react";
import {
  Paper,
  TableBody,
  TableContainer,
  Table,
  TableHead,
  TableCell,
  TableRow,
} from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { bindActionCreators } from "redux";
import { getLeaders } from "../store/Actions";

const useStyles = makeStyles({
  tableContainer: {
    width: "25%",
    minWidth: 300,
    height: "50%",
    minHeight: 330,
    margin: "0 auto",
    marginTop: 140
  },
});

const LeadersTable = (props) => {
  const classes = useStyles();
  const { leaders, getLeaders } = props;

  useEffect(() => {
    getLeaders();
  });

  return (
    <TableContainer
      elevation={7}
      className={classes.tableContainer}
      component={Paper}
    >
      <Table aria-label="a dense table">
        <TableHead>
          <TableCell key="id">№</TableCell>
          <TableCell key="name">Имя</TableCell>
          <TableCell key="wins">Победы</TableCell>
          <TableCell key="score">Очки</TableCell>
        </TableHead>
        <TableBody>
          {leaders.map((leader, index) => (
            <TableRow key={leader.id}>
              <TableCell>{index + 1}</TableCell>
              <TableCell>{leader.name}</TableCell>
              <TableCell>{leader.gamesWon}</TableCell>
              <TableCell>{leader.score}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

LeadersTable.propTypes = {
  leaders: PropTypes.array.isRequired,
};

const mapStateToProps = (state) => ({
  leaders: state.leaders,
});

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators({ getLeaders }, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(LeadersTable);
