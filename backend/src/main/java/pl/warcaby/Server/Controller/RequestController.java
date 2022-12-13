package pl.warcaby.Server.Controller;

import org.json.JSONObject;

public class RequestController {
    public String getRequestType(String request){
        JSONObject json = new JSONObject(request);
        String type = json.getString("type");
        return type;
    }

    public int getGameId(String request){
        JSONObject json = new JSONObject(request);
        int game_id = json.getInt("game_id");
        return game_id;
    }

    public String getCurrentLocation(String request){
        return "xd";
    }

    public String getDesiredLocation(String request){
        return "xd";
    }
}
