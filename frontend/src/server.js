var express = require("express");
var app = express();
var server = require("http").createServer(app);
var io = require("socket.io")(server);
var axios = require("axios");

server.listen(4200, () => {
  console.log("Socket server is started...");
});

var i;

io.on("connection", function (socket) {
  console.log("Connected socket with id: " + socket.id);
  socket.on("disconnect", function () {
    console.log("Socket with id: " + socket.id + " is disconnected");
  });

  socket.on("event:connect", function (data) {
    io.sockets.emit("event:connect", data);
  });

  socket.on("event:answer", function (data) {
    io.sockets.emit("event:answer", data);
    i = 10000;
    while (i--) {
      console.log(i);
    }
    console.log("before patch");
    axios
      .patch("http://localhost:8082/quiz/lobbies/0/proceed?playerId=" + JSON.parse(data))
      .then((response) => {
        io.sockets.emit("event:nextQuestion", JSON.stringify(response.data));
      })
      .catch((e) => {
        console.log(e);
      });
      console.log("after patch");
  });
});
