package pl.warcaby.Checkers;
import java.util.ArrayList;
import java.util.List;

public abstract class Board {

    protected Field[][] fields;

    protected Color turn;

    protected int width;

    protected int heigth;

    public abstract ArrayList<int[]> checkBestPawnMoves(int[] pawnLocation);
    protected abstract ArrayList<ArrayList<int[]>> getAllMoves(int[] pawnLocation);

    public ArrayList<ArrayList<int[]>> checkBestMoves(Color color){
        ArrayList<ArrayList<int[]>> bestMoves = new ArrayList<>();
        int bestMoveLength = 0;
        for(int y = 0; y<this.heigth;y++){
            for(int x = 0; x<this.width;x++){
                if(fields[x][y].getPawnColor()==color){
                    ArrayList<int[]> moves = checkBestPawnMoves(new int[]{x, y});
                    if(moves.size() != 0) {
                        if (moves.get(0)[0] > bestMoveLength) {
                            bestMoves = new ArrayList<>();
                            bestMoveLength = moves.get(0)[0];
                            moves.remove(0);
                            bestMoves.add(moves);
                        } else if (moves.get(0)[0] == bestMoveLength) {
                            moves.remove(0);
                            bestMoves.add(moves);
                        }
                    }
                    else{ return null;}
                }
            }
        }
        return bestMoves;
    }

    public void changeTurn(){
        if(this.turn == Color.BLACK){
            this.turn = Color.WHITE;
        }
        else{
            this.turn = Color.BLACK;
        }
    }
 

}
