package pl.warcaby.Checkers;

import java.util.ArrayList;

public class Bot extends Player{

    public Bot(Color color) {
        super(color);
    }

    public ArrayList<int[]> getBotMove(Board board){
            ArrayList<ArrayList<int[]>> moves = board.checkBestMoves(this.color);
            ArrayList<int[]> botmove = new ArrayList<>();
            botmove.add(moves.get(0).get(1));
            botmove.add(moves.get(0).get(2));
            return botmove;
    }
}
