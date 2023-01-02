package pl.warcaby.Checkers;

/**Klasa pionka, które znajdują się na planszy.*/
public class Pawn {
    /**Gracz będący właścicielem pionka. */
    Player owner;
    /**Typ pionka. Zwykły lub Damka.*/
    PawnType type;
    /**Konstruktor pionka. Domyslnie kazdy pionek jest zwykły.
     * @param owner własciciel pionka*/
    public Pawn(Player owner){
        this.owner = owner;
        this.type = PawnType.NORMAL;
    }
    /**Zwraca kolor pionka na podstawie koloru własciciela.
     * @return kolor pionka.*/
    public Color getColor(){
        return this.owner.getColor();
    }
    /**Zwraca typ pionka zwykly lub damka.
     * @return aktualny typ pionka*/
    public PawnType getType() {
        return type;
    }
    /**Ustawia typ pionka. Setter.
     * @param type nowy typ pionka.*/
    public void setType(PawnType type){
        this.type = type;
    }
}
