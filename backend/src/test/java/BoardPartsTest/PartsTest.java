package BoardPartsTest;

import org.junit.Test;
import pl.warcaby.Checkers.*;

import static org.junit.Assert.assertEquals;


/**Testy jednostkowe dla metod klas znajdujacych sie w planszy.*/
public class PartsTest {

    /**Testy metod klasy Field.*/
    @Test
    public void fieldsMethodsTest(){
        Pawn whitePawn = new Pawn(new Player(Color.WHITE));
        Pawn blackPawn = new Pawn(new Player(Color.BLACK));
        Field field = new Field(whitePawn);
        Field field2 = new Field(blackPawn);

        assertEquals(Color.WHITE, field.getPawnColor());
        assertEquals(PawnType.NORMAL, field.getPawnType());
        assertEquals(Color.BLACK, field2.getPawnColor());

        field.pawnTypeUpgrade();

        assertEquals(PawnType.QUEEN, field.getPawnType());
        assertEquals(whitePawn, field.getOccupied());
        assertEquals(blackPawn, field2.getOccupied());
    }

    /**Testy metod klasy Pawn.*/
    @Test
    public void pawnsMethodsTest(){
        Pawn pawn = new Pawn(new Player(Color.WHITE));
        assertEquals(Color.WHITE, pawn.getColor());
        assertEquals(PawnType.NORMAL, pawn.getType());

        pawn.setType(PawnType.QUEEN);

        assertEquals(PawnType.QUEEN, pawn.getType());
    }
}
