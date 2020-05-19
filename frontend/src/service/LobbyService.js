var axios = require("axios");

const LOBBIES_URL = "/lobbies";

export function createLobby(lobby) {
  return axios.post(LOBBIES_URL, lobby);
}

export function connectToLobby(username, lobbyPass) {
  return axios.patch(LOBBIES_URL + "/0/connect", {
    lobbyPassword: lobbyPass,
    name: username,
  });
}
