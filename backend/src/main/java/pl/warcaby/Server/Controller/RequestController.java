package pl.warcaby.Server.Controller;

import org.json.JSONObject;
import pl.warcaby.Checkers.BoardType;

/**
 * Klasa kontrolera, który obsługuje requesty wysyłane przez klienta
 */
public class RequestController {
    /**
     *
     * @param request request wysłany przez klienta
     * @return typ requestu (create/move/join)
     */
    public String getRequestType(String request){
        JSONObject json = new JSONObject(request);
        return json.getString("type");
    }

    /**
     *
     * @param request request wysłany przez klienta
     * @return id gry
     */
    public int getGameId(String request){
        JSONObject json = new JSONObject(request);
        return json.getInt("game_id");
    }

    /**
     *
     * @param request request wysłany przez klienta
     * @return wariant gry
     */
    public BoardType getVariant(String request){
        JSONObject json = new JSONObject(request);
        String type = json.getString("variant");
        BoardType boardType;
        switch(type){
            case "spanish": boardType = BoardType.SPANISH; break;
            case "polish": boardType = BoardType.POLISH; break;
            case "canadian": boardType = BoardType.CANADIAN; break;
            default: boardType = null;
        }
        return boardType;
    }

    /**
     *
     * @param request request wysłany przez klienta
     * @return lokacja pionka, którym chce ruszyć klient
     */
    public int[] getCurrentLocation(String request){
        JSONObject json = new JSONObject(request);
        int x = json.getInt("currentLocationX");
        int y = json.getInt("currentLocationY");
        return new int[]{x,y};
    }

    /**
     *
     * @param request request wysłany przez klienta
     * @return lokacja, na która klient chce ruszyć pionka
     */
    public int[] getDesiredLocation(String request){
        JSONObject json = new JSONObject(request);
        int x = json.getInt("desiredLocationX");
        int y = json.getInt("desiredLocationY");
        return new int[]{x,y};
    }
}
