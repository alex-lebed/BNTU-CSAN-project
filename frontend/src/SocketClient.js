import io from "socket.io-client";

const host = "http://localhost:4200";

export const socket = io.connect(host);

export const connect = () => {
  return new Promise((resolve, reject) => {
    socket.on("connect", () => resolve(console.log("connected: " + socket.id)));
    socket.on("connect_error", (error) => reject(error));
  });
};

export const emit = (event, data) => {
  socket.emit(event, JSON.stringify(data));
};