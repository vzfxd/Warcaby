package BoardsTest;

import org.junit.Test;
import pl.warcaby.Checkers.Board;
import pl.warcaby.Checkers.Color;
import pl.warcaby.Checkers.Player;
import pl.warcaby.Checkers.Variations.CanadianBoard;

import static org.junit.Assert.assertEquals;

public class CanadianBoardTest {

    Board canadianBoard = new CanadianBoard(new Player(Color.WHITE), new Player(Color.BLACK));

    @Test
    public void getPawnMovesTest(){
        //pionki na lokalizacji 2,4 i 4,4 powinny miec dwa dostepne ruchy (+ pozycja startowa i ilosc bic w outpucie)
        assertEquals(2 + 2, canadianBoard.checkBestPawnMoves(new int[]{2, 4}).size());
        assertEquals(2 + 2, canadianBoard.checkBestPawnMoves(new int[]{4, 4}).size());
        //pionek na lokalizacji 0,4 jeden dostepny ruch
        assertEquals(1 + 2, canadianBoard.checkBestPawnMoves(new int[]{0,4}).size());
    }
}
