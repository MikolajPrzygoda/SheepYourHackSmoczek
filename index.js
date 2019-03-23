const express = require('express');
const app = express();
var http = require('http').Server(app);
var io = require('socket.io')(http);

const PORT = 8888

app.use(express.static('public'));

offers = [
    {"title": "a", "description": "opisA", "cena": 0},
    {"title": "b", "description": "opisB", "cena": 0},
    {"title": "c", "description": "opisC", "cena": 0},
    {"title": "d", "description": "opisD", "cena": 0},
    {"title": "e", "description": "opisE", "cena": 0},
    {"title": "f", "description": "opisF", "cena": 0}
];

io.on('connection', function(socket){
  console.log('a user connected');

    socket.on("getOffers", (callback) => {
        callback(offers);
    });

});

http.listen(PORT, function(){
  console.log('listening on *:8888');
});