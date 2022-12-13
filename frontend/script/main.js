import { CreateRequest, JoinRequest} from "./request";

const joinButton = document.getElementById("join");
const createButton = document.getElementById("create");
let socket;

function establishConnection(url){
    socket = new WebSocket(url);
//    socket.addEventListener("message", (event)=>{responseHandler(event)});
}

//function responseHandler(event){}

function clickHandler(event){
    console.log('xd');
    establishConnection("ws://localhost:8080");
    const src = event.srcElement;
    if(src.id=="join"){
        const game_id = document.querySelector('input[id="game-id"]').value;
        const request = new JoinRequest(game_id);
        console.log(request.toString);
        socket.send(request.toString);
    }else{
        const variant = document.querySelector('input[name="game-variant"]:checked').value;
        const request = new CreateRequest(variant);
        console.log(request.toString);
        socket.send(request.toString);
    }
}

joinButton.addEventListener("click", clickHandler);
createButton.addEventListener("click", clickHandler);