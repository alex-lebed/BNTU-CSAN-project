import React, { useEffect } from "react";
import {
  Paper,
  TableBody,
  TableContainer,
  Table,
  TableHead,
  TableCell,
  TableRow
} from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { bindActionCreators } from "redux";
import { getWinners } from "../store/Actions";

const useStyles = makeStyles({
  tableContainer: {
    width: "25%",
    minHeight: "50%",
    margin: "10% auto",
  },
});

const WinnersTable = (props) => {
  const classes = useStyles();
  const { winners, getWinners } = props;

  useEffect(() => {
    getWinners();
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
          <TableCell key="wins">Количество побед</TableCell>
        </TableHead>
        <TableBody>
          {winners.map((winner, index) => (
            <TableRow key={winner.id}>
              <TableCell>{index + 1}</TableCell>
              <TableCell>{winner.name}</TableCell>
              <TableCell>{winner.winsAmount}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

WinnersTable.propTypes = {
  winners: PropTypes.array.isRequired,
};

const mapStateToProps = (state) => ({
  winners: state.winners,
});

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators({ getWinners }, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(WinnersTable);
