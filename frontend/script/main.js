/**
 * Klasy reprezentujące wysyłane requesty do servera.
 * Wyróżnia się 3 klasy: Create,Join i Move request.
 * Create request jest wysyłany gdy użytkownik chce stworzyć grę,
 * Join request jest wysyłany gdy użytkownik chce dołączyć do gry,
 * Move request jest wysyłany gdy użytkownik chce ruszyć pionkiem
 */
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

class CreateBotRequest{
    constructor(variant){
        this.type = requestType.CREATE_BOT;
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
    MOVE : "MOVE",
    CREATE_BOT : "CREATE_BOT"
}









const joinButton = document.getElementById("join");
const createButton = document.getElementById("create");
const botButton = document.getElementById("bot-play");
const menuContainer = document.querySelector(".menu-container");
const waitingContainer = document.querySelector(".waiting-container");
const gameContainer = document.querySelector(".game-container");
const board = document.querySelector(".board");
const turn_div = document.querySelector(".turn");
let socket;
let selectedPiece;
let playerColor;
let possibleMoves;
let possibleForPiece = [];
let game_id;
let firstField;
let turn;
let result

/**
 * Ustanowienie połączenie z serwerem, poprzez podanie url.
 * Dodaje handler gdy odbierana jest wiadomość od serwera.
 */
function establishConnection(url){
    socket = new WebSocket(url);
    socket.addEventListener("message", (event)=>{
        const response = JSON.parse(event.data)
        /**
         * Serwer wysłał odpowiedz - "game created".
         * Zmienia się graficzny interfejs który pokazuje kod gry
         */
        if(response['feedback']=='game created'){
            let game_id = response['game_id'];
            let code = document.querySelector('.code');
            code.innerHTML = game_id;
            menuContainer.style.display = "none";
            waitingContainer.style.display = "flex";
        }
        /**
         * Serwer wysłał odpowiedz - "game started".
         * Zmienia się graficzny interfejs który pokazuje plansze
         */
        if(response['feedback']=='game started'){
            turn = response['turn'];
            firstField = response['firstField'];
            playerColor = response['color']
            possibleMoves = response['possibleMoves'];
            game_id = response['game_id'];
            createBoard(response['board'], firstField);
            turn_div.innerHTML = turn;
            waitingContainer.style.display = "none";
            menuContainer.style.display = "none";
            gameContainer.style.display = "flex";
            if(playerColor=="WHITE") document.body.style.backgroundColor = "DarkGoldenRod"; else document.body.style.backgroundColor = "DarkRed";
        }
        /**
         * Serwer wysłał odpowiedz - "player moved".
         * Aktualizuje się ustawienie pionków
         */
        if(response['feedback']=='player moved'){
            possibleMoves = response['possibleMoves'];
            turn = response['turn'];
            turn_div.innerHTML = turn;
            board.innerHTML = '';
            createBoard(response['board'],firstField);
        }
        /**
         * Serwer wysłał odpowiedz - "game finished".
         * Pojawia się komunikat o przegranej lub wygranej
         */
        if(response['feedback']=='game finished'){
            console.log("finished");
            let winner = response['winner'];
            if(winner==playerColor){
                result = "YOU WON"
            }else{
                result = "YOU LOST"
            }
            gameContainer.innerHTML = "<h1>"+result+"</h1>"
        }
    });
}

/**
 * Handler przycisków do tworzenia gry i dołączania do gry
 */
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

/**
 * 
 * @param {*} responseBoard  plansza w formacie String[][], gdzie *=biały pionek, #=czarny pionek, .=puste pole
 * @param {*} firstField kolor pierwszego pola na dole po lewej stronie
 */
function createBoard(responseBoard, firstField){
    let i = 0
    let size = responseBoard.length;
    for(let y=size-1;y>=0;y--){
        row_div = document.createElement('div');
        row_div.classList.add('row-'+y,'row');
        board.appendChild(row_div);
        if(y==size-1) firstField = changeColor(firstField);
        for(let x=0;x<size;x++){
            if(x!=0) firstField = changeColor(firstField);
            field = document.createElement('div');
            field.addEventListener("click",fieldHandler);
            field.classList.add('col-'+x,firstField);
            row_div.appendChild(field);
            if(responseBoard[x][0][y]!="."){
                piece = document.createElement('div');
                piece.classList.add("piece");
                switch(responseBoard[x][0][y]){
                    case '@':piece.style.backgroundColor = "red";
                            piece.style.border = "thick solid blue";
                            if(playerColor=="BLACK") piece.addEventListener("click",pieceHandler);
                        break;
                    case '#':piece.style.backgroundColor = "red";
                            if(playerColor=="BLACK") piece.addEventListener("click",pieceHandler);
                        break;
                    case '&':piece.style.backgroundColor = "yellow";
                            piece.style.border = "thick solid blue";
                            if(playerColor=="WHITE") piece.addEventListener("click",pieceHandler);
                        break;
                    case '*':piece.style.backgroundColor = "yellow";
                            if(playerColor=="WHITE") piece.addEventListener("click",pieceHandler);
                        break;

                }
                piece.style.borderRadius = "25px";
                field.appendChild(piece);
            } 
        }
    }
}

/**
 * 
 * @param {*} field pole dla którego chcemy poznać współrzędne
 * @returns koordynaty x,y podanego pola
 */
function getFieldLocation(field){
    let x = field.classList[0].split('-')[1];
    let row = field.parentNode;
    let y = row.classList[0].split('-')[1];
    return [x,y];
}

/**
 * 
 * @param {*} piece pionek dla którego chcemy poznać współrzędne
 * @returns koordynaty x,y podanego pionka
 */
function getPieceLocation(piece){
    let col = piece.parentNode;
    let row = col.parentNode;
    let x = col.classList[0].split('-')[1];
    let y = row.classList[0].split('-')[1];
    return [x,y];
}

/**
 * Handler, który obsługuje klikniecia na pola
 * @param {*} event 
 */
function fieldHandler(event){
    let field = event.srcElement;
    if(selectedPiece && field.classList[0].split('-')[0]=="col"){
        let [x,y] = getFieldLocation(field);
        for(item of possibleForPiece){
            if(item[0]==x && item[1]==y){
                let [pieceX,pieceY] = getPieceLocation(selectedPiece)
                msg = {
                    "type":"MOVE",
                    "game_id":game_id,
                    "currentLocationX":pieceX,
                    "currentLocationY":pieceY,
                    "desiredLocationX":x,
                    "desiredLocationY":y
            }
                selectedPiece = null;
                socket.send(JSON.stringify(msg));
            }
        }
    }
}

/**
 * Handler, który osbługuje kliknięcia na pionki
 * @param {*} event 
 */
function pieceHandler(event){
    undoHighlight(possibleForPiece);
    selectedPiece = event.srcElement;
    let [x,y] = getPieceLocation(selectedPiece);
    possibleForPiece = [];
    for(piece of possibleMoves){
        if(piece[1][0]==x && piece[1][1]==y){
            for(field of piece){
                possibleForPiece.push(field);
            }
        }
    }
    highlightField(possibleForPiece);
}

/**
 * Podświetlanie pól, na które może ruszyć się pionek
 * @param {*} possibleForPiece  pola na które może ruszyć się pionek
 */
function highlightField(possibleForPiece){
    let ilosc_bic = possibleForPiece[0][0];
    let przeskok;
    if(ilosc_bic == 0){
        przeskok = 1;
    }else{
        przeskok = ilosc_bic[0];
    }
    for(i=2; i<possibleForPiece.length; i+=przeskok){
        let fieldToHighlight = document.querySelector(".row-"+possibleForPiece[i][1]).children[possibleForPiece[i][0]];
        fieldToHighlight.style.boxSizing = "border-box";
        fieldToHighlight.style.border = "thick solid blue";
    }
}

/**
 * Usunięcie podświetlenia pól, na które może ruszyć się pionek
 * @param {*} possibleForPiece pola na które może ruszyć się pionek
 */
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