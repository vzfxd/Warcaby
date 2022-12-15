package pl.warcaby.Server.Controller;

import org.json.JSONObject;
import pl.warcaby.Checkers.BoardType;

public class RequestController {
    public String getRequestType(String request){
        JSONObject json = new JSONObject(request);
        return json.getString("type");
    }

    public int getGameId(String request){
        JSONObject json = new JSONObject(request);
        return json.getInt("game_id");
    }

    public BoardType getVariant(String request){
        JSONObject json = new JSONObject(request);
        String type = json.getString("variant");
        BoardType boardType;
        switch(type){
            case "spanish": boardType = BoardType.SPANISH; break;
            case "german": boardType = BoardType.GERMAN; break;
            case "turkish": boardType = BoardType.TURKISH; break;
            default: boardType = null;
        }
        return boardType;
    }

    public String getCurrentLocation(String request){
        return "xd";
    }

    public String getDesiredLocation(String request){
        return "xd";
    }
}
