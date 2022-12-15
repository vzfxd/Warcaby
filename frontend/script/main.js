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
const board = document.querySelector(".board");
let socket;
let selectedPiece;
let playerColor;
let possibleMoves;
let possibleForPiece = [];

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
        if(response['feedback']=='game started'){
            let turn = response['turn'];
            let board = response['board'];
            let firstField = response['firstField'];
            playerColor = response['color']
            possibleMoves = response['possibleMoves'];
            createBoard(board, firstField);
            turn_div = document.querySelector(".turn");
            turn_div.innerHTML = turn;
            waitingContainer.style.display = "none";
            menuContainer.style.display = "none";
            gameContainer.style.display = "flex";
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

function changeColor(color){
    if(color=="BLACK"){
        return "WHITE";
    }else{
        return "BLACK";
    }
}

function createBoard(responseBoard, firstField){
    let i = 0
    for(let y=7;y>=0;y--){
        row_div = document.createElement('div');
        row_div.classList.add('row-'+y,'row');
        board.appendChild(row_div);
        if(y==7) firstField = changeColor(firstField);
        for(let x=0;x<8;x++){
            if(x!=0) firstField = changeColor(firstField);
            field = document.createElement('div');
            field.classList.add('col-'+x,firstField);
            row_div.appendChild(field);
            if(responseBoard[x][0][y] == "*" || responseBoard[x][0][y]=="#"){
                piece = document.createElement('div');
                piece.classList.add("piece");
                if(responseBoard[x][0][y]=="*"){
                    piece.style.backgroundColor = "yellow";
                    if(playerColor=="WHITE") piece.addEventListener("click",pieceHandler);
                }else {
                    piece.style.backgroundColor = "red";
                    if(playerColor=="BLACK") piece.addEventListener("click",pieceHandler);
                }
                piece.style.borderRadius = "25px";
                field.appendChild(piece);
            } 
        }
    }
}

function pieceHandler(event){
    undoHighlight(possibleForPiece);
    selectedPiece = event.srcElement;
    let col = selectedPiece.parentNode;
    let row = col.parentNode;
    let x = col.classList[0].split('-')[1];
    let y = row.classList[0].split('-')[1];
    possibleForPiece = [];
    for(piece of possibleMoves){
        if(piece[0][0]==x && piece[0][1]==y){
            for(field of piece){
                if(field[0]!=x && field[1]!=y){
                    possibleForPiece.push(field);
                }
            }
        }
    }
    highlightField(possibleForPiece);
}

function highlightField(possibleForPiece){
    for(field of possibleForPiece){
        let fieldToHighlight = document.querySelector(".row-"+field[1]).children[field[0]];
        fieldToHighlight.style.boxSizing = "border-box";
        fieldToHighlight.style.border = "thick solid blue";
    }
}

function undoHighlight(possibleForPiece){
    for(field of possibleForPiece){
        let fieldToHighlight = document.querySelector(".row-"+field[1]).children[field[0]];
        fieldToHighlight.style.boxSizing = "border-box";
        fieldToHighlight.style.border = "";
    }
}

joinButton.addEventListener("click", clickHandler);
createButton.addEventListener("click", clickHandler);
establishConnection("ws://localhost:8080");