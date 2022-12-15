class CreateRequest{
    constructor(variant){
        this.type = requestType.CREATE;
        this.variant = variant;
    }

    toString(){
        let msg = {
            type: this.type,
            variant: this.variant
        }

        return JSON.stringify(msg);
    }
}

class JoinRequest{
    constructor(game_id){
        this.type = requestType.JOIN;
        this.game_id = game_id;
    }

    toString(){
        let msg = {
            type: this.type,
            game_id: this.game_id
        }

        return JSON.stringify(msg);
    }
}

class MoveRequest{
    constructor(currentLocation,desiredLocation,game_id){
        this.type = requestType.MOVE;
        this.game_id = game_id;
        this.currentLocation = currentLocation;
        this.desiredLocation = desiredLocation;
    }

    toString(){
        let msg = {
            type: this.type,
            game_id: this.game_id,
            currentLocation: this.currentLocation,
            desiredLocation: this.desiredLocation
        }

        return JSON.stringify(msg);
    }
}

const requestType = {
    CREATE : "CREATE",
    JOIN : "JOIN",
    MOVE : "MOVE"
}









const joinButton = document.getElementById("join");
const createButton = document.getElementById("create");
const menuContainer = document.querySelector(".menu-container");
const waitingContainer = document.querySelector(".waiting-container");
const gameContainer = document.querySelector(".game-container");
let socket;

function establishConnection(url){
    socket = new WebSocket(url);
    socket.addEventListener("message", (event)=>{
        const response = JSON.parse(event.data)
        if(response['feedback']=='game created'){
            let game_id = response['game_id'];
            let code = document.querySelector('.code');
            code.innerHTML = game_id;
            menuContainer.style.display = "none";
            waitingContainer.style.display = "flex";
        }
    });
}

function clickHandler(event){
    const src = event.srcElement;
    if(src.id=="join"){
        const game_id = document.querySelector('input[id="game-id"]').value;
        if(game_id!=""){
            const request = new JoinRequest(game_id);
            socket.send(request.toString());
        }
    }else{
        const variant = document.querySelector('input[name="game-variant"]:checked').getAttribute('id');
        const request = new CreateRequest(variant);
        socket.send(request.toString());
    }
}

joinButton.addEventListener("click", clickHandler);
createButton.addEventListener("click", clickHandler);
establishConnection("ws://localhost:8080");