package pl.warcaby.Checkers.Variations;

import pl.warcaby.Checkers.*;
import pl.warcaby.Checkers.BoardType;

import java.util.ArrayList;

/**Kanadyjski wariant gry w warcaby. Wariant ten różni się od polskiego jedynie rozmiarem planszy dlatego metody są
 * dziedziczone od PolishBoard.*/
public class CanadianBoard extends PolishBoard {
    /**Kontruktor tworzący plansze 12x12 z pionkami na czarnych polach.
     * @param whitePlayer gracz biały.
     * @param blackPlayer gracz czarny.*/
    public CanadianBoard(Player whitePlayer, Player blackPlayer) {
        this.width = 12;
        this.heigth = 12;
        this.fields = new Field[12][12];
        for (int y = 0; y < 12; y++) {
            for (int x = 0; x < 12; x++) {
                if (y < 5 && (x + y) % 2 == 0) {
                    this.fields[x][y] = new Field(new Pawn(whitePlayer));
                } else if (y > 6 && (x + y) % 2 == 0) {
                    this.fields[x][y] = new Field(new Pawn(blackPlayer));
                } else {
                    this.fields[x][y] = new Field();
                }
            }
        }
        this.turn = Color.WHITE;
    }
}
