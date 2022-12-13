package pl.warcaby.Server.Controller;

import org.json.JSONObject;

public class RequestController {
    public String getRequestType(String request){
        JSONObject json = new JSONObject(request);
        String type = json.getString("type");
        return type;
    }

    public String getGameId(String request){
        return "xd";
    }

    public String getCurrentLocation(String request){
        return "xd";
    }

    public String getDesiredLocation(String request){
        return "xd";
    }
}
