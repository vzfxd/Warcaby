package pl.warcaby.Server.Controller;

import pl.warcaby.Checkers.BoardType;
import pl.warcaby.Checkers.Color;
import pl.warcaby.Checkers.Field;
import pl.warcaby.Checkers.Player;
import pl.warcaby.Checkers.Variations.SpanishBoard;
import pl.warcaby.Server.Game;

import java.util.ArrayList;
import java.util.List;

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

    public Boolean joinGame(Player player, int game_id){
        Game game = findGame(game_id);
        if(game.getPlayerList().size()<2){
            game.addPlayer(player);
            switch(game.getBoardType()){
                case SPANISH -> game.setBoard(new SpanishBoard(game.getPlayerList().get(0),game.getPlayerList().get(1)));
            }
            return true;
        }
        return false;
    }

    public String[][] printBoard(int game_id){
        Game game = findGame(game_id);
        Field[][] fields = game.getBoardFields();
        String[][] printedBoard = new String[game.getBoardSize()[1]][1];
        int i = 0;
        for(Field[] row: fields){
            for(Field field: row){
                if(field.getPawnColor() != null && field.getPawnColor().equals(Color.BLACK)){
                    if(printedBoard[i][0] == null) printedBoard[i][0] = "#"; else printedBoard[i][0] += "#";
                } else if(field.getPawnColor() != null && field.getPawnColor().equals(Color.WHITE)){
                    if(printedBoard[i][0] == null) printedBoard[i][0] = "*"; else printedBoard[i][0] += "*";
                }else{
                    if(printedBoard[i][0] == null) printedBoard[i][0] = "."; else printedBoard[i][0] += ".";
                }
            }
            i++;
        }
        return printedBoard;
    }
}
