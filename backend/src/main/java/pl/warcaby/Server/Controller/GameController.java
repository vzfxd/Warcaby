package pl.warcaby.Server.Controller;

import pl.warcaby.Checkers.Board;
import pl.warcaby.Checkers.BoardType;
import pl.warcaby.Checkers.Player;
import pl.warcaby.Checkers.Variations.SpanishBoard;
import pl.warcaby.Server.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameController {
    List<Game> gameList = new ArrayList<>();

    public int createGame(Player player, BoardType boardType){
        Game game = new Game(boardType);
        game.addPlayer(player);
        gameList.add(game);
        return game.getGame_id();
    }

    public Game findGame(int game_id){
        for(Game game: gameList){
            if(game.getGame_id() == game_id) return game;
        }
        return null;
    }

    public void joinGame(Player player, int game_id){
        Game game = findGame(game_id);
        game.addPlayer(player);
        switch(game.getBoardType()){
            case SPANISH -> game.setBoard(new SpanishBoard(game.getPlayerList().get(0),game.getPlayerList().get(1)));
        }
    }
}
