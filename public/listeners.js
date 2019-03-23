var socket = io();

var currentOffers;

function loadOffers(){
    socket.emit("getOffers", (data) => {
        currentOffers = data;
    })
}

