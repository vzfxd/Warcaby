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
import java.util.List;

public class Server extends WebSocketServer {

    private static final GameController gameController = new GameController();
    private static final RequestController requestController = new RequestController();
    private static final ResponseController responseController = new ResponseController();

    public Server(int port){
        super(new InetSocketAddress(port));
    }
    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {}

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {}

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        String requestType = requestController.getRequestType(s);
        if(requestType.equals("CREATE")) {
            Player player = new Player(Color.WHITE, webSocket);
            int game_id = gameController.createGame(player, requestController.getVariant(s));
            responseController.createResponse(webSocket, game_id);
        } else if (requestType.equals("JOIN")) {
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
        }else {
            int[] currentLocation = requestController.getCurrentLocation(s);
            int[] desiredLocation = requestController.getDesiredLocation(s);
            int game_id = requestController.getGameId(s);
            Game game = gameController.findGame(game_id);
            Color turn = gameController.move(game_id,currentLocation,desiredLocation);
            String[][] printedBoard = gameController.printBoard(game_id);
            JSONObject response = responseController.moveResponse(printedBoard,turn);
            responseController.broadcast(game.getPlayerList(),response,game.getBoard());
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }
}
