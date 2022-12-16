package pl.warcaby.Checkers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Field {
    Pawn occupied = null;

    public Color getPawnColor() {
        if (occupied != null) {return this.occupied.getColor();}
        return null;
    }

    public void pawnTypeUpgrade(){
        if(this.occupied!=null) {this.occupied.setType(PawnType.QUEEN);}
    }

    public PawnType getPawnType(){
        if(this.occupied!=null){return this.occupied.getType();}
        return null;
    }

    public Pawn getOccupied(){ return this.occupied;}

    public void setOccupied(Pawn pawn){
        this.occupied = pawn;
    }
}
