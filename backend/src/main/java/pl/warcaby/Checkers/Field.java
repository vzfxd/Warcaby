package pl.warcaby.Checkers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Field {
    Pawn occupied = null;

    public Color getPawnColor() {
        if (occupied != null) {
            return this.occupied.getColor();
        }
        return null;
    }

    public void pawnTypeUpgrade(){
        this.occupied.setType(PawnType.QUEEN);
    }

    public PawnType getPawnType(){
        return this.occupied.getType();
    }

    public Pawn getOccupied(){ return this.occupied;}

    public void setOccupied(Pawn pawn){
        this.occupied = pawn;
    }
}
