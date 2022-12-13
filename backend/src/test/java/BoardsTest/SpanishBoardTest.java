package BoardsTest;

import org.junit.Test;
import pl.warcaby.Checkers.Board;
import pl.warcaby.Checkers.Color;
import pl.warcaby.Checkers.Player;
import pl.warcaby.Checkers.Variations.SpanishBoard;

import static org.junit.Assert.*;

public class SpanishBoardTest {

    /**Tablica z rozstawieniem początkowym.*/
    Board spanishBoard = new SpanishBoard(new Player(Color.WHITE), new Player(Color.BLACK));

    @Test
    public void getPawnMovesTest(){

        //pionek w rogu planszy nie powinien mieć żadnych dostepnych ruchów
        assertNull(spanishBoard.checkBestPawnMoves(new int[]{0,0}));
        //bialy pionek z przodu powinien miec dwa dostepne ruchy
        assertEquals(2,spanishBoard.checkBestPawnMoves(new int[]{1,2}).size());
        //bialy pionek z przodu na rogu powinien miec jeden dostepny ruch
        assertEquals(1,spanishBoard.checkBestPawnMoves(new int[]{7,2}).size());
        spanishBoard.changeTurn();
        //czarny pionek z przodu na rogu powinien miec jeden dostepny ruch
        assertEquals(1,spanishBoard.checkBestPawnMoves(new int[]{0,5}).size());
    }

    @Test
    public void wrongTurnTest(){
        //Metoda nie zwroci zadnych wynikow dla uzytkownika ktory czeka na ruch przeciwnika
        assertNull(spanishBoard.checkBestPawnMoves(new int[]{2,5}));
        spanishBoard.changeTurn();
        assertNotNull(spanishBoard.checkBestPawnMoves(new int[]{2,5}));
    }


}
