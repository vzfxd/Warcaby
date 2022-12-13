package pl.warcaby.Checkers.Variations;

import pl.warcaby.Checkers.*;
import java.util.ArrayList;

public class SpanishBoard extends Board {

    public SpanishBoard(Player whitePlayer, Player blackPlayer){
        this.width = 8;
        this.heigth = 8;
        this.fields = new Field[8][8];
        for(int y = 0; y<8;y++){
            for(int x = 0; x<8;x++){
                if(y<3 && (x+y)%2==0) {
                    this.fields[x][y] = new Field(new Pawn(blackPlayer));
                }
                else if(y>4 && (x+y)%2==0){
                    this.fields[x][y] = new Field(new Pawn(whitePlayer));
                }
                else{
                    this.fields[x][y] = new Field();
                }
            }
        }
        this.turn = Color.WHITE;
    }
    @Override
    public ArrayList<int[]> checkBestPawnMoves(int[] pawnLocation) {
        return null;
    }

}
