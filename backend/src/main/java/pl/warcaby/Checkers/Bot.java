package pl.warcaby.Checkers;

import java.util.ArrayList;

public class Bot extends Player{

    Board board;

    public Bot(Color color) {
        super(color);
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public ArrayList<int[]> getBotMove(){
        if(this.board!=null){
            ArrayList<ArrayList<int[]>> moves = this.board.checkBestMoves(this.color);
            return moves.get(0);
        }
        return null;
    }
}
