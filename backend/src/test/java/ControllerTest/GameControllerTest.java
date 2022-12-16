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
    public void moveTest(){
        GameController gameController = new GameController();
        Player whitePlayer = new Player(Color.WHITE);
        int game_id = gameController.createGame(whitePlayer,BoardType.SPANISH);
        Player blackPlayer = new Player(Color.BLACK);
        gameController.joinGame(blackPlayer,game_id);
        Board board = gameController.findGame(game_id).getBoard();
        for(Field[] row: board.getFields()){
            for(Field field: row){
                field.setOccupied(null);
            }
        }

        board.getFields()[2][6].setOccupied(new Pawn(whitePlayer));
        board.getFields()[5][5].setOccupied(new Pawn(blackPlayer));

        gameController.move(game_id, new int[]{2,6}, new int[]{1,7});
        gameController.move(game_id, new int[]{5,5}, new int[]{4,4});

//        gameController.move(game_id, new int[]{1,7}, new int[]{0,6});
//        gameController.move(game_id, new int[]{6,4}, new int[]{7,3});
//
//        gameController.move(game_id, new int[]{0,6}, new int[]{5,1});
//        gameController.move(game_id, new int[]{7,3}, new int[]{6,2});

        ArrayList<ArrayList<int[]>> possible = gameController.findGame(game_id).getBoard().checkBestMoves(Color.WHITE);
        for(ArrayList<int[]> move: possible){
            for(int[] xd: move){
                System.out.println(xd[0]+" "+xd[1]);
            }
        }

    }
}
