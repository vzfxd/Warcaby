package pl.warcaby.Checkers;

public enum BoardType {
    SPANISH("SPANISH"),GERMAN("GERMAN"),TURKISH("TURKISH");

    String type;
    BoardType(String type){
        this.type = type;
    }
}
