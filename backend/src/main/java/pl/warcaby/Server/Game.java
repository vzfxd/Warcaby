package pl.warcaby.Server;

import pl.warcaby.Checkers.Board;
import pl.warcaby.Checkers.BoardType;
import pl.warcaby.Checkers.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private int game_id;
    private List<Player> playerList;
    private Board board;
    private BoardType boardType;

    public Game(BoardType boardType){
        Random rand = new Random();
        this.game_id = rand.nextInt((999999-100000)+1)+100000;
        this.boardType = boardType;
        this.playerList = new ArrayList<>();
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public int getGame_id() {
        return game_id;
    }

    public void addPlayer(Player player){
        playerList.add(player);
    }

    public List<Player> getPlayerList(){
        return playerList;
    }

    public BoardType getBoardType() {
        return boardType;
    }
}
