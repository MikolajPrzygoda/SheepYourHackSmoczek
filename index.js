const express = require('express');
const app = express();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var fs = require("fs");

const PORT = 8888;

app.use(express.static('public'));

offers = loadOffers();

io.on('connection', function(socket){
  console.log('a user connected');

    socket.on("getOffers", (callback) => {
        callback(offers);
    });

});

http.listen(PORT, function(){
  console.log('listening on *:8888');
});

function storeOffers(offers){
    fs.writeFile("offers.json", JSON.stringify( offers ), "utf8");
}

function loadOffers(){
    return require("./offers.json");
}