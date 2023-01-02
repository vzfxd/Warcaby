package pl.warcaby.Checkers;

public enum BoardType {
    SPANISH("spanish"),POLISH("polish"),CANADIAN("canadian");

    String type;
    BoardType(String type){
        this.type = type;
    }
}
