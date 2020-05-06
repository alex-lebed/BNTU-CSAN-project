import axios from "axios";

export const LobbyService = {
  createLobby
};

const LOBBIES_URL = "/lobbies";

function createLobby(lobby) {
  return axios.post(LOBBIES_URL, lobby)
}