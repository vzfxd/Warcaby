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

    @Test
    public void victoryTest(){
        GameController gameController = new GameController();
        Player whitePlayer = new Player(Color.WHITE);
        Player blackPlayer = new Player(Color.BLACK);
        int game_id = gameController.createGame(whitePlayer,BoardType.SPANISH);
        gameController.joinGame(blackPlayer,game_id);
        Board board = gameController.findGame(game_id).getBoard();
;        for(Field[] row: board.getFields()){
            for(Field field: row){
                field.setOccupied(null);
            }
        }

        board.getFields()[0][0].setOccupied(new Pawn(whitePlayer));
        board.getFields()[1][1].setOccupied(new Pawn(blackPlayer));
        Color color = gameController.victory(game_id);
        System.out.println(color);
        gameController.move(game_id,new int[]{0,0},new int[]{2,2});
        color = gameController.victory(game_id);
        System.out.println(color);
    }
}
