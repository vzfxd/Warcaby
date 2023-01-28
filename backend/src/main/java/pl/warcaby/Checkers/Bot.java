package pl.warcaby.Checkers;

import java.util.ArrayList;

public class Bot extends Player{

    public Bot(Color color) {
        super(color);
    }

    public ArrayList<int[]> getBotMove(Board board){
            ArrayList<ArrayList<int[]>> moves = board.checkBestMoves(this.color);
            return moves.get(0);
    }
}
