package pl.warcaby.Server;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import pl.warcaby.Checkers.Color;
import pl.warcaby.Checkers.Player;
import pl.warcaby.Server.Controller.GameController;
import pl.warcaby.Server.Controller.RequestController;
import pl.warcaby.Server.Controller.ResponseController;

import java.net.InetSocketAddress;

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
            gameController.joinGame(new Player(Color.BLACK,webSocket), requestController.getGameId(s));
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }
}
