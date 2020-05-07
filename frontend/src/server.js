var express = require("express");
var app = express();
var server = require("http").createServer(app);
var io = require("socket.io")(server);

app.use(express.static(__dirname + "/node_modules"));
app.get("/", function (req, res, next) {
  res.sendFile(__dirname + "../public/index.html");
});

server.listen(4200, () => {
  console.log("Socket server is started...");
});

io.on("connection", function (socket) {
  console.log("Connected socket with id: " + socket.id);
  socket.on("disconnect", function () {
    console.log("Socket with id: " + socket.id + " is disconnected");
  });

  socket.on("event:connect", function(data) {
      io.sockets.emit("event:connect", data);
  }) 
});
