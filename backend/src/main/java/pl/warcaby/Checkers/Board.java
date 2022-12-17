package pl.warcaby.Checkers;
import java.util.ArrayList;


/**Abstrakcyjna klasa planszy na której toczy się rozgrywka gry w warcaby.*/
public abstract class Board {
    /**Pola planszy, dla każdej odmiany gry rozmiar tej tablicy może być rózny.*/
    protected Field[][] fields;
    /**Kolor gracza który aktualnie może wykonać ruch.*/
    protected Color turn;
    /**Szerokosc tablicy.*/
    protected int width;
    /**Wysokosc tablicy.*/
    protected int heigth;
    /**Abstrakcyjna metoda wyświetlająca najlepsze ruchy dla pionka na podanej lokalizacji.
     *
     * @param pawnLocation lokalizacja pionka na planszy.
     * @return Lista dostępnych ruchów dla pionka.*/
    public abstract ArrayList<int[]> checkBestPawnMoves(int[] pawnLocation);
    /**Abstrakcyjna metoda znajdująca wszystkie możliwe ruchy dla pionka na podanej lokazlizacji.
     *
     * @param pawnLocation lokalizacja pionka na planszy.
     * @return Listy z ruchami dostępnymi dla pionka.*/
    protected abstract ArrayList<ArrayList<int[]>> getAllMoves(int[] pawnLocation);

    /**Metoda szukająca możliwych bić na planszy dla gracza z danym kolorem.
     *
     * @param color kolor gracza, dla którego bic szukamy.
     * @return Listy dostępnych najlepszych bić dla gracza.*/
    public ArrayList<ArrayList<int[]>> checkBestMoves(Color color){
        ArrayList<ArrayList<int[]>> bestMoves = new ArrayList<>();
        int bestMoveLength = 0;
        for(int y = 0; y<this.heigth;y++){
            for(int x = 0; x<this.width;x++){
                if(fields[x][y].getPawnColor()==color){
                    ArrayList<int[]> moves = checkBestPawnMoves(new int[]{x, y});
                    if(moves != null && moves.size() != 0) {
                        if (moves.get(0)[0] > bestMoveLength) {
                            bestMoves = new ArrayList<>();
                            ArrayList<int[]> move = new ArrayList<>();
                            move.add(moves.get(0));
                            move.add(moves.get(1));
                            move.add(moves.get(2));
                            bestMoveLength = moves.get(0)[0];
                            if(moves.get(0)[0]>1){
                                for(int i=1;i<(moves.size()/bestMoveLength)-1;i++){
                                    move.add(moves.get(2+(i*bestMoveLength)));
                                }
                            }
                            else{
                                for(int i=3;i<moves.size();i++){
                                    move.add(moves.get(i));
                                }
                            }
                            bestMoves.add(move);
                        }else if(bestMoveLength == 0 && moves.get(0)[0]==0){
                            bestMoves.add(moves);
                        }
                        else if (moves.get(0)[0] == bestMoveLength) {
                            ArrayList<int[]> move = new ArrayList<>();
                            move.add(moves.get(0));
                            move.add(moves.get(1));
                            move.add(moves.get(2));
                            for(int i=3;i<moves.size();i++){
                                move.add(moves.get(i));
                            }
                            bestMoves.add(move);
                        }
                    }
                }
            }
        }
        return bestMoves;
    }
    /**Metoda zmieniajaca kolejke na drugiego gracza.*/
    public void changeTurn(){
        if(this.turn == Color.BLACK){
            this.turn = Color.WHITE;
        }
        else{
            this.turn = Color.BLACK;
        }
    }

    public Color getTurn(){
        return this.turn;
    }

    /**Abstrakcyjna metoda wykonywania ruchu na planszy.
     *
     * @param steps Lista zawierająca opis ruchu.*/
    public abstract void move(ArrayList<int[]> steps);
    /**Metoda zwracająca tablice z polami planszy.
     *
     * @return tablica z polami planszy.*/
    public Field[][] getFields(){
        return this.fields;
    }
    /**Metoda zwracajaca wymiary planszy.
     *
     * @return szerokosc i wysokosc planszy.*/
    public int[] getSize(){
        return new int[]{this.width,this.heigth};
    }

}
