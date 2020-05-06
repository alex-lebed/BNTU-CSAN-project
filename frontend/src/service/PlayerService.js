import axios from 'axios';

export const PlayerService = {
  getWinners,
};

const PLAYERS_URL = "/players";

function getWinners() {
  return axios.get(PLAYERS_URL + '/winners');
}
