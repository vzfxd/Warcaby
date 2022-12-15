package pl.warcaby.Checkers;

import lombok.AllArgsConstructor;
import org.java_websocket.WebSocket;

@AllArgsConstructor
public class Player {

    Color color;

    WebSocket webSocket;

    public Player(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setWebSocket(WebSocket webSocket){
        this.webSocket = webSocket;
    }
}
