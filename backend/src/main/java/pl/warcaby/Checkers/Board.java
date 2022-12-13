package pl.warcaby.Checkers;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Board {

    Field[][] fields;

    Color turn;

    public abstract ArrayList<int[]> checkBestPawnMoves();

    public abstract ArrayList<int[][]> checkBestMoves();

}
