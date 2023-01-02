package pl.warcaby.Checkers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**Klasa pola na planszy. Kazda plansza posiada wiele pól.*/
@NoArgsConstructor
@AllArgsConstructor
public class Field {
    /**Pionek znajdujący się na polu. Domyslnie null.*/
    Pawn occupied = null;
    /**Getter na kolor pionka na polu jezeli taki istnieje.
     * @return kolor pionka na polu lub null jezeli nie znajduje sie na nim zaden.*/
    public Color getPawnColor() {
        if (occupied != null) {return this.occupied.getColor();}
        return null;
    }
    /**Metoda ulepszająca zwykłego pionka znajdującego się na polu do damki.*/
    public void pawnTypeUpgrade(){
        if(this.occupied!=null) {this.occupied.setType(PawnType.QUEEN);}
    }
    /**Getter na typ pionka jeżeli istnieje.
     * @return Typ pionka znajdujacego sie na polu lub null jezeli nie istnieje.*/
    public PawnType getPawnType(){
        if(this.occupied!=null){return this.occupied.getType();}
        return null;
    }
    /**Getter na pionka znajdujacego sie na polu.
     * @return pionek na tym polu.*/
    public Pawn getOccupied(){ return this.occupied;}
    /**Setter na pionka na polu.
     * @param pawn pionek.*/
    public void setOccupied(Pawn pawn){
        this.occupied = pawn;
    }
}
