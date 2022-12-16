package pl.warcaby.Server.Controller;

import org.java_websocket.WebSocket;
import org.json.JSONObject;
import pl.warcaby.Checkers.Board;
import pl.warcaby.Checkers.Color;
import pl.warcaby.Checkers.Player;

import java.util.ArrayList;
import java.util.List;

public class ResponseController{
    public void createResponse(WebSocket webSocket, int game_id){
        JSONObject json = new JSONObject();
        json.put("feedback","game created");
        json.put("game_id",game_id);
        webSocket.send(json.toString());
    }

    public JSONObject joinResponse(String[][] printedBoard, Color firstField, int game_id){
        JSONObject json = new JSONObject();
        json.put("feedback","game started");
        json.put("board",printedBoard);
        json.put("turn",Color.WHITE);
        json.put("firstField",firstField);
        json.put("game_id",game_id);
        return json;
    }

    public JSONObject moveResponse(String[][] printedBoard,Color color){
        JSONObject json = new JSONObject();
        json.put("feedback","player moved");
        json.put("board",printedBoard);
        json.put("turn",color);
        return  json;
    }

    public void broadcast(List<Player> playerList,JSONObject response,Board board){
        for(Player player: playerList){
            response.put("color",player.getColor());
            ArrayList<ArrayList<int[]>> possibleMoves = board.checkBestMoves(player.getColor());
            response.put("possibleMoves",possibleMoves);
            player.getWebSocket().send(response.toString());
        }
    }
}
