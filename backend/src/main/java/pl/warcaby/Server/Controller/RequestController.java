package pl.warcaby.Server.Controller;

import org.json.JSONArray;
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
            case "polish": boardType = BoardType.POLISH; break;
            case "canadian": boardType = BoardType.CANADIAN; break;
            default: boardType = null;
        }
        return boardType;
    }

    public int[] getCurrentLocation(String request){
        JSONObject json = new JSONObject(request);
        int x = json.getInt("currentLocationX");
        int y = json.getInt("currentLocationY");
        return new int[]{x,y};
    }

    public int[] getDesiredLocation(String request){
        JSONObject json = new JSONObject(request);
        int x = json.getInt("desiredLocationX");
        int y = json.getInt("desiredLocationY");
        return new int[]{x,y};
    }
}
