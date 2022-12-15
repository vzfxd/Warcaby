package pl.warcaby.Server.Controller;

import org.json.JSONObject;

public class RequestController {
    public String getRequestType(String request){
        JSONObject json = new JSONObject(request);
        return json.getString("type");
    }

    public int getGameId(String request){
        JSONObject json = new JSONObject(request);
        return json.getInt("game_id");
    }

    public String getVariant(String request){
        JSONObject json = new JSONObject(request);
        return json.getString("variant");
    }

    public String getCurrentLocation(String request){
        return "xd";
    }

    public String getDesiredLocation(String request){
        return "xd";
    }
}
