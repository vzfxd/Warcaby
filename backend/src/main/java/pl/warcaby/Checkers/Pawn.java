package pl.warcaby.Checkers;

public class Pawn {

    Player owner;

    PawnType type;

    public Pawn(Player owner){
        this.owner = owner;
        this.type = PawnType.NORMAL;
    }

    public Color getColor(){
        return this.owner.getColor();
    }

    public PawnType getType() {
        return type;
    }

    public void setType(PawnType type){
        this.type = type;
    }
}
