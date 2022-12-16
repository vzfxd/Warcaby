package ControllerTest;

import org.junit.Test;
import pl.warcaby.Checkers.*;
import pl.warcaby.Server.Controller.GameController;
import pl.warcaby.Server.Game;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameControllerTest {

    @Test
    public void printBoardTest(){
        GameController gameController = new GameController();
        int game_id = gameController.createGame(new Player(Color.WHITE),BoardType.SPANISH);
        gameController.joinGame(new Player(Color.BLACK),game_id);
        String[][] actualBoard = new String[][]{
                {"*.*...#."},
                {".*...#.#"},
                {"*.*...#."},
                {".*...#.#"},
                {"*.*...#."},
                {".*...#.#"},
                {"*.*...#."},
                {".*...#.#"},
        };
        assertEquals(actualBoard,gameController.printBoard(game_id));
    }
}
