package ControllerTest;

import org.junit.Test;
import pl.warcaby.Checkers.BoardType;
import pl.warcaby.Server.Controller.RequestController;

import static org.junit.Assert.assertEquals;

public class RequestControllerTest {

    @Test
    public void getRequestTypeTest(){
        RequestController requestController = new RequestController();
        String request = "{\"type\":\"JOIN\",\"game_id\":\"123456\"}";
        assertEquals("JOIN",requestController.getRequestType(request));
    }

    @Test
    public void getGameIdTest(){
        RequestController requestController = new RequestController();
        String request = "{\"type\":\"JOIN\",\"game_id\":\"123456\"}";
        assertEquals(123456 ,requestController.getGameId(request));
    }

    @Test
    public void getVariantTest(){
        RequestController requestController = new RequestController();
        String request = "{\"type\":\"CREATE\",\"variant\":\"spanish\"}";
        assertEquals(BoardType.SPANISH,requestController.getVariant(request));
    }
}
