package pl.warcaby.Checkers.Variations;

import pl.warcaby.Checkers.*;

import java.util.ArrayList;


public class CanadianBoard extends PolishBoard {

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
