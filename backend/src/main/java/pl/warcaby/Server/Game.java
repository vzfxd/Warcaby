package pl.warcaby.Server;

import pl.warcaby.Checkers.Board;
import pl.warcaby.Checkers.Player;

import java.util.List;
import java.util.Random;

public class Game {
    int game_id;
    List<Player> playerList;
    Board board;

    public Game(Board board){
        Random rand = new Random();
        this.board = board;
        this.game_id = rand.nextInt((999999-100000)+1)+100000;
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
}
