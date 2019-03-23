var socket = io();

function loadOffers(){
    socket.emit("getOffers", (data) => {
        console.log(data);
    })
}