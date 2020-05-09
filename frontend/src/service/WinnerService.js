import axios from "axios";

export const WinnerService = {
  getWinners,
};

const WINNERS_URL = "/winners";

function getWinners() {
  return axios.get(WINNERS_URL, { params: { limit: 5 } });
}
