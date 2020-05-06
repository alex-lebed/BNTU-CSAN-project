import axios from "axios";

export const LobbyService = {
  createLobby,
  getCurrentLobby
};

const LOBBIES_URL = "/lobbies";

function createLobby(lobby) {
  return axios.post(LOBBIES_URL, lobby)
}

function getCurrentLobby() {
    return new Promise((resolve, reject) =>
      resolve({
        data: {
          id: 0,
          admin: "admin",
          password: "kek",
          players: [],
          playersAmountToStart: 5,
          questions: [],
          currentQuestionIndex: 0,
          status: "WAITING_PLAYERS"
        }
      })
    );
  }