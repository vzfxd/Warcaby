package pl.warcaby.Checkers;

import java.util.ArrayList;
import java.util.Random;

public class Bot extends Player{

    public Bot(Color color) {
        super(color);
    }

    public ArrayList<int[]> getBotMove(Board board){
            Random rand = new Random();
            ArrayList<ArrayList<int[]>> moves = board.checkBestMoves(this.color);
            ArrayList<int[]> botmove = new ArrayList<>();
            int n = rand.nextInt(moves.size());
            int m = rand.nextInt(moves.get(n).size()-2);
            botmove.add(moves.get(n).get(1));
            botmove.add(moves.get(n).get(m+2));
            return botmove;
    }
}
