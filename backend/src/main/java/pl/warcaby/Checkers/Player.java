package pl.warcaby.Checkers;

import lombok.AllArgsConstructor;
import org.java_websocket.WebSocket;

@AllArgsConstructor
public class Player {

    Color color;

    WebSocket webSocket;

    public Color getColor() {
        return color;
    }
}
