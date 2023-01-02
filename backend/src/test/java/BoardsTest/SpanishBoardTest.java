package BoardsTest;

import org.java_websocket.WebSocket;
import org.junit.Test;
import pl.warcaby.Checkers.Board;
import pl.warcaby.Checkers.Color;
import pl.warcaby.Checkers.Player;
import pl.warcaby.Checkers.Variations.SpanishBoard;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;

/**Klasa testująca działanie metod planszy hiszpańskiej.*/
public class SpanishBoardTest {

    /**Tablica z rozstawieniem początkowym.*/
    Board spanishBoard = new SpanishBoard(new Player(Color.WHITE), new Player(Color.BLACK));

    /**Test znajdywania najlepszych ruchów dla pojedynczego pionka*/
    @Test
    public void getPawnMovesTest(){

        //pionek w rogu planszy nie powinien mieć żadnych dostepnych ruchów
        assertNull(spanishBoard.checkBestPawnMoves(new int[]{0,0}));
        //bialy pionek z przodu powinien miec dwa dostepne ruchy
        assertEquals(4,spanishBoard.checkBestPawnMoves(new int[]{2,2}).size());
        //bialy pionek z przodu na rogu powinien miec jeden dostepny ruch
        assertEquals(3,spanishBoard.checkBestPawnMoves(new int[]{0,2}).size());
        spanishBoard.changeTurn();
        //czarny pionek z przodu na rogu powinien miec jeden dostepny ruch
        assertEquals(3,spanishBoard.checkBestPawnMoves(new int[]{7,5}).size());
    }
    /**Test czy metoda znajdywania ruchów dla pionka znajduje ruchy tylko dla gracza, którego kolejka jest teraz.*/
    @Test
    public void wrongTurnTest(){
        //Metoda nie zwroci zadnych wynikow dla uzytkownika ktory czeka na ruch przeciwnika
        assertNull(spanishBoard.checkBestPawnMoves(new int[]{1,5}));
        spanishBoard.changeTurn();
        assertNotNull(spanishBoard.checkBestPawnMoves(new int[]{1,5}));
    }
    /**Test znajdywania najlepszych możliwych ruchow dla gracza oraz wykonywania ruchow pionkami.*/
    @Test
    public void bestMovesForPlayer(){
        //Metoda zwroci mozliwosci ruchu dla 4 pionkow przy rozstawieniu początkowym
        assertEquals(4,spanishBoard.checkBestMoves(Color.WHITE).size());

        //przesuwanie pionka z (0,2) na (1,3) oraz z (3,5) na (2,4)
        ArrayList<int[]> moves = new ArrayList<>();
        moves.add(new int[]{0,2});
        moves.add(new int[]{1,3});
        spanishBoard.move(moves);

        moves = new ArrayList<>();
        moves.add(new int[]{3,5});
        moves.add(new int[]{2,4});
        spanishBoard.move(moves);

        //Metoda checkBestPawn powinna pokazac dostepne bicie
        ArrayList<int[]> possibleMove = spanishBoard.checkBestPawnMoves(new int[]{1,3});

        assertEquals(Arrays.toString(new int[]{1,1}), Arrays.toString(possibleMove.get(0)));
        assertEquals(Arrays.toString(new int[]{1,3}), Arrays.toString(possibleMove.get(1)));
        assertEquals(Arrays.toString(new int[]{3,5}), Arrays.toString(possibleMove.get(2)));
        assertNotNull(spanishBoard.checkBestMoves(Color.WHITE));
    }

    @Test
    public void bestMovesForColor(){
        ArrayList<int[]> moves = new ArrayList<>();
        moves.add(new int[]{0,2});
        moves.add(new int[]{1,3});
        spanishBoard.move(moves);

        moves = new ArrayList<>();
        moves.add(new int[]{3,5});
        moves.add(new int[]{2,4});
        spanishBoard.move(moves);

        ArrayList<ArrayList<int[]>> bestMoves = spanishBoard.checkBestMoves(Color.WHITE);
        assertEquals(Arrays.toString(new int[]{1,1}), Arrays.toString(bestMoves.get(0).get(0)));
        assertEquals(Arrays.toString(new int[]{1,3}), Arrays.toString(bestMoves.get(0).get(1)));
        assertEquals(Arrays.toString(new int[]{3,5}), Arrays.toString(bestMoves.get(0).get(2)));


    }

}
