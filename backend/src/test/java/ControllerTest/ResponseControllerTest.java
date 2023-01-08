package ControllerTest;

import org.json.JSONObject;
import org.junit.Test;
import pl.warcaby.Checkers.Color;
import pl.warcaby.Server.Controller.ResponseController;

import static org.junit.Assert.assertEquals;

public class ResponseControllerTest {
    @Test
    public void moveResponseTest(){
        ResponseController responseController = new ResponseController();
        String[][] board = new String[][]{{"test"}};
        JSONObject response = responseController.moveResponse(board, Color.WHITE);
        JSONObject json = new JSONObject();
        json.put("feedback","player moved");
        json.put("board",board);
        json.put("turn",Color.WHITE);
        assertEquals(json.toString(),response.toString());
    }

    @Test
    public void joinResponseTest(){
        ResponseController responseController = new ResponseController();
        String[][] board = new String[][]{{"test"}};
        JSONObject response = responseController.joinResponse(board, Color.WHITE,123);
        JSONObject json = new JSONObject();
        json.put("feedback","game started");
        json.put("board",board);
        json.put("turn",Color.WHITE);
        json.put("firstField",Color.WHITE);
        json.put("game_id",123);
        assertEquals(json.toString(),response.toString());
    }
}
