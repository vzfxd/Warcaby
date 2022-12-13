package pl.warcaby.Checkers;
import java.util.ArrayList;

public abstract class Board {

    Field[][] fields;

    Color turn;

    private int width;

    private int heigth;

    public abstract ArrayList<int[]> checkBestPawnMoves(int[] pawnLocation);

    public ArrayList<ArrayList<int[]>> checkBestMoves(Color color){
        ArrayList<ArrayList<int[]>> bestMoves = null;
        int bestMoveLength = 0;
        for(int y = 0; y<this.heigth;y++){
            for(int x = 0; x<this.width;x++){
                if(fields[y][x].getPawnColor()==color){
                    ArrayList<int[]> moves = checkBestPawnMoves(new int[]{x, y});
                    if(moves.get(0)[0]>bestMoveLength){
                        bestMoves = new ArrayList<>();
                        bestMoveLength = moves.get(0)[0];
                        moves.remove(0);
                        bestMoves.add(moves);
                    }
                    else if(moves.get(0)[0]==bestMoveLength && bestMoves != null){
                        moves.remove(0);
                        bestMoves.add(moves);
                    }
                }
            }
        }
        return bestMoves;
    }

}
