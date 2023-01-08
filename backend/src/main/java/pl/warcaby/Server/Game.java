package pl.warcaby.Server;

import pl.warcaby.Checkers.Board;
import pl.warcaby.Checkers.BoardType;
import pl.warcaby.Checkers.Field;
import pl.warcaby.Checkers.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasa reprezentująca gre
 */
public class Game {
    /**
     * Id gry
     */
    private final int game_id;
    /**
     * Lista graczy
     */
    private final List<Player> playerList;
    /**
     * Plansza
     */
    private Board board;
    /**
     * Wariant planszy
     */
    private final BoardType boardType;

    public Game(BoardType boardType){
        Random rand = new Random();
        this.game_id = rand.nextInt((999999-100000)+1)+100000;
        this.boardType = boardType;
        this.playerList = new ArrayList<>();
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public Field[][] getBoardFields(){
        return this.board.getFields();
    }

    public int getGame_id() {
        return game_id;
    }

    /**
     * Metoda dodająca gracza do gry
     * @param player gracz, którego chcemy dodać
     */
    public void addPlayer(Player player){
        playerList.add(player);
    }

    public List<Player> getPlayerList(){
        return playerList;
    }

    public BoardType getBoardType() {
        return boardType;
    }

    public int[] getBoardSize(){
        return this.board.getSize();
    }

    public Board getBoard(){
        return this.board;
    }
}
