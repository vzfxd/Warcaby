package pl.warcaby.Server;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;
import pl.warcaby.Checkers.Board;
import pl.warcaby.Checkers.Color;
import pl.warcaby.Checkers.Player;
import pl.warcaby.Server.Controller.GameController;
import pl.warcaby.Server.Controller.RequestController;
import pl.warcaby.Server.Controller.ResponseController;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa Serwera
 */
public class Server extends WebSocketServer {

    /**
     * Kontroler do zarządzania grami odbywającami sie na serwerze
     */
    private static final GameController gameController = new GameController();
    /**
     * Kontroler do zarządzania requestami odbieranymi od klientów
     */
    private static final RequestController requestController = new RequestController();
    /**
     * Kontroler do zarządzania odpowiedziami wysyłanymi do klientów w odpowiedzi na ich requesty
     */
    private static final ResponseController responseController = new ResponseController();

    public Server(int port){
        super(new InetSocketAddress(port));
    }
    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {}

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {}

    public void createRequestProcedure(WebSocket webSocket, String s){
        Player player = new Player(Color.WHITE, webSocket);
        int game_id = gameController.createGame(player, requestController.getVariant(s));
        responseController.createResponse(webSocket, game_id);
        System.out.println("CREATE");
    }

    public void joinRequestProcedure(WebSocket webSocket, String s){
        int game_id = requestController.getGameId(s);
        Boolean joined = gameController.joinGame(new Player(Color.BLACK,webSocket), game_id);
        if(joined){
            String[][] printedBoard = gameController.printBoard(game_id);
            List<Player> playerList = gameController.findGame(game_id).getPlayerList();
            Color firstField = gameController.getFirstField(game_id);
            Board board = gameController.getGameBoard(game_id);
            JSONObject response = responseController.joinResponse(printedBoard,firstField,game_id);
            responseController.broadcast(playerList,response,board);
        }
    }

    public void responseProcedure(int game_id,Game game,Color turn){
        String[][] printedBoard = gameController.printBoard(game_id);
        JSONObject response = responseController.moveResponse(printedBoard,turn);
        responseController.broadcast(game.getPlayerList(),response,game.getBoard());
    }

    public void moveRequestProcedure(String s){
        int[] currentLocation = requestController.getCurrentLocation(s);
        int[] desiredLocation = requestController.getDesiredLocation(s);
        int game_id = requestController.getGameId(s);
        Game game = gameController.findGame(game_id);
        Color turn = gameController.move(game_id,currentLocation,desiredLocation);
        Color victory = gameController.victory(game_id);
        if(victory == null){
            responseProcedure(game_id,game,turn);
            if(gameController.checkBotExistence(game_id)){
                while (turn.equals(Color.BLACK)){
                    ArrayList<int[]> moves = gameController.getBotMoves(game.getBoard());
                    turn = gameController.move(game_id,moves.get(0),moves.get(1));
                    victory = gameController.victory(game_id);
                    if(victory == null) responseProcedure(game_id,game,turn); else responseController.gameFinished(game.getPlayerList(),victory);
                }
            }
        }else{
            responseController.gameFinished(game.getPlayerList(),victory);
        }
    }

    public void createBotRequestProcedure(WebSocket webSocket, String s){
        Player player = new Player(Color.WHITE, webSocket);
        int game_id = gameController.createBotGame(player, requestController.getVariant(s));
        String[][] printedBoard = gameController.printBoard(game_id);
        List<Player> playerList = gameController.findGame(game_id).getPlayerList();
        Color firstField = gameController.getFirstField(game_id);
        Board board = gameController.getGameBoard(game_id);
        JSONObject response = responseController.joinResponse(printedBoard,firstField,game_id);
        responseController.broadcast(playerList,response,board);
        System.out.println("BOT");
    }

    /**
     * Metoda wykonująca się podczas każdego otrzymania wiadomości od klienta
     * @param webSocket webSocket klienta który wysyła request
     * @param s request wysłany przez klienta
     */
    @Override
    public void onMessage(WebSocket webSocket, String s) {
        String requestType = requestController.getRequestType(s);
        switch (requestType) {
            case "CREATE" -> createRequestProcedure(webSocket, s);
            case "JOIN" -> joinRequestProcedure(webSocket, s);
            case "MOVE" -> moveRequestProcedure(s);
            case "CREATE_BOT" -> createBotRequestProcedure(webSocket,s);
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }
}
