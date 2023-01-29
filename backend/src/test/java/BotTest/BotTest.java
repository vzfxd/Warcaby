package BotTest;

import org.junit.Test;
import pl.warcaby.Checkers.Bot;
import pl.warcaby.Checkers.Color;
import pl.warcaby.Checkers.Player;
import pl.warcaby.Checkers.Variations.CanadianBoard;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BotTest {

    @Test
    public void getBotMoveTest(){
        Bot bot = new Bot(Color.BLACK);
        CanadianBoard board = new CanadianBoard(new Player(Color.WHITE), bot);
        ArrayList<int[]> move = new ArrayList<>();
        move.add(new int[]{2, 4});
        move.add(new int[]{3, 5});
        board.move(move);
        move = new ArrayList<>();
        move.add(new int[]{5, 7});
        move.add(new int[]{4, 6});
        board.move(move);
        board.changeTurn();

        ArrayList<int[]> expected = new ArrayList<>();
        expected.add(new int[]{4, 6});
        expected.add(new int[]{2, 4});

        assertEquals(expected.get(0)[0], bot.getBotMove(board).get(0)[0]);
        assertEquals(expected.get(0)[1], bot.getBotMove(board).get(0)[1]);

        assertEquals(expected.get(1)[0], bot.getBotMove(board).get(1)[0]);
        assertEquals(expected.get(1)[1], bot.getBotMove(board).get(1)[1]);
    }
}
