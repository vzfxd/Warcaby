package BoardsTest;

import org.junit.Test;
import pl.warcaby.Checkers.Board;
import pl.warcaby.Checkers.Color;
import pl.warcaby.Checkers.Player;
import pl.warcaby.Checkers.Variations.PolishBoard;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PolishBoardTest {
    Board polishBoard = new PolishBoard(new Player(Color.WHITE), new Player(Color.BLACK));

    @Test
    public void getPawnMovesTest(){
        // pionek z rogu planszy powinien miec 0 ruchow
        assertNull(polishBoard.checkBestPawnMoves(new int[]{0,0}));
        // pionek na lokalizacji 1,3 powinien miec dwa dostepne ruchy( return funkcji = 2 + 2 poniewaz pierwsze dwa indexy to
        // ilosc bitych pionkow oraz pozycja startowa )
        assertEquals(2 + 2, polishBoard.checkBestPawnMoves(new int[]{1, 3}).size());
        // pionek na lokalizacji 9,3 powinien miec 1 dostepny ruch ( size = 2 + 1 )
        assertEquals(2 + 1, polishBoard.checkBestPawnMoves(new int[]{9, 3}).size());

        ArrayList<int[]> moveWhite = new ArrayList<>();
        ArrayList<int[]> moveBlack = new ArrayList<>();
        moveWhite.add(new int[]{3, 3});
        moveWhite.add(new int[]{4, 4});
        moveBlack.add(new int[]{2, 6});
        moveBlack.add(new int[]{3, 5});
        polishBoard.move(moveWhite);
        polishBoard.move(moveBlack);

        //po wykonaniu takich ruchow bialy pionek (4, 4) powinien miec dostepne bicie
        assertEquals(1, polishBoard.checkBestPawnMoves(new int[]{4, 4}).get(0)[0]);
        // bicie z (4, 4)
        assertEquals(4, polishBoard.checkBestPawnMoves(new int[]{4, 4}).get(1)[0]);
        assertEquals(4, polishBoard.checkBestPawnMoves(new int[]{4, 4}).get(1)[1]);
        // na (2, 6)
        assertEquals(2, polishBoard.checkBestPawnMoves(new int[]{4, 4}).get(2)[0]);
        assertEquals(6, polishBoard.checkBestPawnMoves(new int[]{4, 4}).get(2)[1]);
    }

    @Test
    public void reverseCaptureTest(){
        polishBoard = new PolishBoard(new Player(Color.WHITE), new Player(Color.BLACK));
        ArrayList<int[]> moveWhite = new ArrayList<>();
        ArrayList<int[]> moveBlack = new ArrayList<>();
        moveWhite.add(new int[]{3, 3});
        moveWhite.add(new int[]{5, 5});
        moveBlack.add(new int[]{2, 6});
        moveBlack.add(new int[]{4, 4});
        polishBoard.move(moveWhite);
        polishBoard.move(moveBlack);

        ArrayList<int[]> results = polishBoard.checkBestPawnMoves(new int[]{5, 5});
        // w takim ulozozeniu bialy pionek (5, 5) powinien miec dostepne bicie do tylu
        assertEquals(1, results.get(0)[0]);
        // bicie z (5, 5)
        assertEquals(5, results.get(1)[0]);
        assertEquals(5, results.get(1)[1]);
        // na (3, 3)
        assertEquals(3, results.get(2)[0]);
        assertEquals(3, results.get(2)[1]);
    }

    @Test
    public void bestMovesForPlayer(){
        //Metoda zwroci mozliwosci ruchu dla 5 pionkow przy rozstawieniu początkowym
        assertEquals(5,polishBoard.checkBestMoves(Color.WHITE).size());

        //przesuwanie pionka z (9,3) na (8,4) oraz z (6,6) na (7,5)
        ArrayList<int[]> moves = new ArrayList<>();
        moves.add(new int[]{9,3});
        moves.add(new int[]{8,4});
        polishBoard.move(moves);

        moves = new ArrayList<>();
        moves.add(new int[]{6,6});
        moves.add(new int[]{7,5});
        polishBoard.move(moves);

        //Metoda checkBestPawn powinna pokazac dostepne bicie
        ArrayList<int[]> possibleMove = polishBoard.checkBestPawnMoves(new int[]{8,4});
        assertEquals(Arrays.toString(new int[]{1,1}), Arrays.toString(possibleMove.get(0)));
        assertEquals(Arrays.toString(new int[]{8,4}), Arrays.toString(possibleMove.get(1)));
        assertEquals(Arrays.toString(new int[]{6,6}), Arrays.toString(possibleMove.get(2)));
        assertNotNull(polishBoard.checkBestMoves(Color.WHITE));
    }
}
