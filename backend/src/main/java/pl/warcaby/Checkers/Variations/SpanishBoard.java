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
        ArrayList<int[]> bestMoves =  new ArrayList<>();
        int x = pawnLocation[0];
        int y = pawnLocation[1];
        int bestMoveLength = 0;
        if(this.fields[x][y].getPawnColor()==this.turn){
            ArrayList<ArrayList<int[]>> allMoves = getAllMoves(pawnLocation);
            for (ArrayList<int[]> move : allMoves) {
                int len = move.get(0)[0];
                if (len > bestMoveLength) {
                    bestMoves = new ArrayList<>();
                    for (int j = 0; j < len + 2; j++) {
                        bestMoves.add(move.get(j));
                    }
                    bestMoveLength = len;
                } else if (len == bestMoveLength) {
                    int lim = len + 2;
                    if (len == 0) {
                        lim = 3;
                    }
                    for (int j = 2; j < lim; j++) {
                        bestMoves.add(move.get(j));
                    }
                }
            }
            return bestMoves;

        }
        else return null;
    }

    @Override
    protected ArrayList<ArrayList<int[]>> getAllMoves(int[] pawnLocation) {
        return null;
    }

}
