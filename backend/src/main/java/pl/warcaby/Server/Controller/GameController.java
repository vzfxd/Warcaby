package pl.warcaby.Server.Controller;

import pl.warcaby.Checkers.*;
import pl.warcaby.Checkers.Variations.CanadianBoard;
import pl.warcaby.Checkers.Variations.PolishBoard;
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
        if(game!=null && game.getPlayerList().size()<2){
            game.addPlayer(player);
            switch(game.getBoardType()){
                case SPANISH -> game.setBoard(new SpanishBoard(game.getPlayerList().get(0),game.getPlayerList().get(1)));
                case POLISH -> game.setBoard(new PolishBoard(game.getPlayerList().get(0),game.getPlayerList().get(1)));
                case CANADIAN -> game.setBoard(new CanadianBoard(game.getPlayerList().get(0),game.getPlayerList().get(1)));
            }
            return true;
        }
        return false;
    }

    public Color getFirstField(int game_id){
        Game game = findGame(game_id);
        if(game.getBoardType().equals(BoardType.SPANISH)) return Color.WHITE; else return Color.BLACK;
    }

    public Board getGameBoard(int game_id){
        Game game = findGame(game_id);
        return game.getBoard();
    }

    public ArrayList<ArrayList<int[]>> getPossibleMoves(int game_id, Color color){
        Game game = findGame(game_id);
        return game.getBoard().checkBestMoves(color);
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

    public Color move(int game_id,int[] currentLocation,int[] desiredLocation) {
        Game game = findGame(game_id);
        Board board = game.getBoard();
        int iloscBic = board.checkBestMoves(board.getTurn()).get(0).get(0)[0];
        if(iloscBic<2) board.changeTurn();
        ArrayList<int[]> steps = new ArrayList<>();
        steps.add(currentLocation);
        steps.add(desiredLocation);
        board.move(steps);
        return board.getTurn();
    }

    public Color victory(int game_id){
        Game game = findGame(game_id);
        Board board = game.getBoard();
        Color turn = board.getTurn();
        if(board.checkBestMoves(turn).size()==0){
            if(turn.equals(Color.WHITE)) return Color.BLACK; else return Color.WHITE;
        }
        return null;
    }
}
