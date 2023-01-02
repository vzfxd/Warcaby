package pl.warcaby.Checkers;
/**Enum z typami planszy.*/
public enum BoardType {
    SPANISH("spanish"),POLISH("polish"),CANADIAN("canadian");

    final String type;
    BoardType(String type){
        this.type = type;
    }
}
