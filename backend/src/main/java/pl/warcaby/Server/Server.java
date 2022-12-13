package pl.warcaby.Server;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import pl.warcaby.Server.Controller.GameController;
import pl.warcaby.Server.Controller.RequestController;

import java.net.InetSocketAddress;

public class Server extends WebSocketServer {

    private static final GameController gameController = new GameController();
    private static final RequestController requestController = new RequestController();

    public Server(int port){
        super(new InetSocketAddress(port));
    }
    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {}

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {}

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        String type = requestController.getRequestType(s);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }
}
