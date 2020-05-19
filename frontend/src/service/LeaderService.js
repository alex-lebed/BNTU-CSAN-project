import axios from "axios";

export const LeaderService = {
  getLeaders,
};

const LEADERS_URL = "/leaders";

function getLeaders() {
  return axios.get(LEADERS_URL, { params: { limit: 5 } });
}
