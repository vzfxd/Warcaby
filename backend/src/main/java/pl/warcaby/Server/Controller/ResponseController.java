package pl.warcaby.Server.Controller;

import org.java_websocket.WebSocket;
import org.json.JSONObject;
import pl.warcaby.Checkers.Color;
import pl.warcaby.Checkers.Player;
import pl.warcaby.Server.Game;

import java.util.List;

public class ResponseController{
    public void createResponse(WebSocket webSocket, int game_id){
        JSONObject json = new JSONObject();
        json.put("feedback","game created");
        json.put("game_id",game_id);
        webSocket.send(json.toString());
    }

    public JSONObject joinResponse(String[][] printedBoard, Color color){
        JSONObject json = new JSONObject();
        json.put("feedback","game started");
        json.put("board",printedBoard);
        json.put("color",color);
        json.put("turn",Color.WHITE);
        return json;
    }

    public void broadcast(String[][] printedBoard, List<Player> playerList){
        for(Player player: playerList){
            JSONObject response = joinResponse(printedBoard,player.getColor());
            player.getWebSocket().send(response.toString());
        }
    }
}
