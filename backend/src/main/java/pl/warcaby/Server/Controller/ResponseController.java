package pl.warcaby.Server.Controller;

import org.java_websocket.WebSocket;
import org.json.JSONObject;
import pl.warcaby.Checkers.Board;
import pl.warcaby.Checkers.Color;
import pl.warcaby.Checkers.Player;
import pl.warcaby.Server.Game;

import java.util.ArrayList;
import java.util.List;

public class ResponseController{
    public void createResponse(WebSocket webSocket, int game_id){
        JSONObject json = new JSONObject();
        json.put("feedback","game created");
        json.put("game_id",game_id);
        webSocket.send(json.toString());
    }

    public JSONObject joinResponse(String[][] printedBoard, Color color, Color firstField, ArrayList<ArrayList<int[]>> possibleMoves){
        JSONObject json = new JSONObject();
        json.put("feedback","game started");
        json.put("board",printedBoard);
        json.put("color",color);
        json.put("turn",Color.WHITE);
        json.put("firstField",firstField);
        json.put("possibleMoves",possibleMoves);
        return json;
    }

    public void broadcast(String[][] printedBoard, List<Player> playerList,Color firstField, Board board){
        for(Player player: playerList){
            ArrayList<ArrayList<int[]>> possibleMoves = board.checkBestMoves(player.getColor());
            JSONObject response = joinResponse(printedBoard,player.getColor(),firstField,possibleMoves);
            player.getWebSocket().send(response.toString());
        }
    }
}
