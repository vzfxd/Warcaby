package ControllerTest;

import org.junit.Test;
import pl.warcaby.Server.Controller.RequestController;

import static org.junit.Assert.assertEquals;

public class RequestControllerTest {

    @Test
    public void getRequestTypeTest(){
        RequestController requestController = new RequestController();
        String request = "{\"type\":\"JOIN\",\"game_id\":\"123456\"}";
        assertEquals("JOIN",requestController.getRequestType(request));
    }
}
