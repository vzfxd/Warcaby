package pl.warcaby.Server.Controller;

import org.java_websocket.WebSocket;
import org.json.JSONObject;

public class ResponseController{
    public void createResponse(WebSocket webSocket, int game_id){
        JSONObject json = new JSONObject();
        json.put("feedback","game created");
        json.put("game_id",game_id);
        webSocket.send(json.toString());
    }

    public void joinResponse(){};
}
