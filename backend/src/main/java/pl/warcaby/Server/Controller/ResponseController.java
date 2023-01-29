package pl.warcaby.Server.Controller;

import org.java_websocket.WebSocket;
import org.json.JSONObject;
import pl.warcaby.Checkers.Board;
import pl.warcaby.Checkers.Bot;
import pl.warcaby.Checkers.Color;
import pl.warcaby.Checkers.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa kontrolera, który zarządza odpowiedziami wysyłanymi do klientów
 */
public class ResponseController{
    /**
     * Odpowiedz wysyłana, gdy klient chce stworzyć gre
     * @param webSocket webSocket klienta
     * @param game_id wygenerowane id stworzonej gry
     */
    public void createResponse(WebSocket webSocket, int game_id){
        JSONObject json = new JSONObject();
        json.put("feedback","game created");
        json.put("game_id",game_id);
        webSocket.send(json.toString());
    }

    /**
     * Odpowiedz wysyłana, gdy klient chce dołączyć do gry
     * @param printedBoard plansza w formacie String[][] gdzie * to biały pionek, a # czarny
     * @param firstField kolor pierwszego pola na dole po lewej
     * @param game_id id gry
     * @return odpowiedz w formacie json
     */
    public JSONObject joinResponse(String[][] printedBoard, Color firstField, int game_id){
        JSONObject json = new JSONObject();
        json.put("feedback","game started");
        json.put("board",printedBoard);
        json.put("turn",Color.WHITE);
        json.put("firstField",firstField);
        json.put("game_id",game_id);
        return json;
    }

    /**
     *
     * @param printedBoard plansza w formacie String[][] gdzie * to biały pionek, a # czarny
     * @param color aktualna tura
     * @return odpowiedz w formacie json
     */
    public JSONObject moveResponse(String[][] printedBoard,Color color){
        JSONObject json = new JSONObject();
        json.put("feedback","player moved");
        json.put("board",printedBoard);
        json.put("turn",color);
        return  json;
    }

    /**
     * Metoda która wysyła odpowiedz graczy w grze
     * @param playerList lista graczy w grze
     * @param response odpowiedz w formacie json
     * @param board plansza w grze
     */
    public void broadcast(List<Player> playerList,JSONObject response,Board board){
        for(Player player: playerList){
            if(!(player instanceof Bot)){
                response.put("color",player.getColor());
                ArrayList<ArrayList<int[]>> possibleMoves = board.checkBestMoves(player.getColor());
                response.put("possibleMoves",possibleMoves);
                player.getWebSocket().send(response.toString());
            }
        }
    }

    /**
     * Metoda która wysyła odpowiedz do graczy w grze, gdy gra została zakończona
     * @param playerList lista graczy w grze
     * @param color kolor wygranego
     */
    public void gameFinished(List<Player> playerList, Color color){
        JSONObject json =  new JSONObject();
        json.put("feedback","game finished");
        json.put("winner",color);
        for(Player player: playerList){
            player.getWebSocket().send(json.toString());
        }
    }
}
