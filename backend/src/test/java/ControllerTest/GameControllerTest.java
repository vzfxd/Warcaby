package ControllerTest;

import org.junit.Test;
import pl.warcaby.Checkers.Board;
import pl.warcaby.Checkers.BoardType;
import pl.warcaby.Checkers.Color;
import pl.warcaby.Checkers.Player;
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
    public void moveTest(){
        GameController gameController = new GameController();
        int game_id = gameController.createGame(new Player(Color.WHITE),BoardType.SPANISH);
        Game game = gameController.findGame(game_id);
        gameController.joinGame(new Player(Color.BLACK),game_id);
        Board board = game.getBoard();
        ArrayList<ArrayList<int[]>> bestMoves = board.checkBestMoves(Color.WHITE);
        gameController.move(game_id,new int[]{2,2},new int[]{1,3});
        String[][] actualBoard = new String[][]{
                {"*.*...#."},
                {".*.*.#.#"},
                {"*.....#."},
                {".*...#.#"},
                {"*.*...#."},
                {".*...#.#"},
                {"*.*...#."},
                {".*...#.#"},
        };
        ArrayList<ArrayList<int[]>> bestMovesblack = board.checkBestMoves(Color.BLACK);
        assertEquals(actualBoard,gameController.printBoard(game_id));
    }
}
