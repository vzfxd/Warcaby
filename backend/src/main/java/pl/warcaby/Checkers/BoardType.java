package pl.warcaby.Checkers;

public enum BoardType {
    SPANISH("spanish"),GERMAN("german"),TURKISH("turkish");

    String type;
    BoardType(String type){
        this.type = type;
    }
}
