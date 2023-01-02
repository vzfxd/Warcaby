package pl.warcaby.Checkers;

import lombok.AllArgsConstructor;
import org.java_websocket.WebSocket;

/** Klasa gracza, ktory bedzie bral udzial w grze w warcaby.
 *  Klasa zawiera konstruktor przyjmujacy wszystkie argumenty oraz konstuktor przyjmujacy wylacznie kolor gracza na
 *  potrzeby testow jednostkowych.
 * */
@AllArgsConstructor
public class Player {
    /**Kolor gracza. Biały lub Czarny.*/
    Color color;

    WebSocket webSocket;

    /**Konstuktor przyjmujący wyłącznie kolor gracza na potrzeby testów jednostkowych.
     * @param color kolor gracza.*/
    public Player(Color color){
        this.color = color;
    }
    /**Metoda zwracajaca kolor gracza. Getter.
     * @return kolor gracza*/
    public Color getColor() {
        return color;
    }
    /**Metoda ustawiajacą webSocket gracza. Setter.
     * @param webSocket nowy websocket gracza.*/
    public void setWebSocket(WebSocket webSocket){
        this.webSocket = webSocket;
    }
    /**Metoda zwracająca webSocket gracza. Getter.
     * @return webSocket gracza.*/
    public WebSocket getWebSocket() { return this.webSocket;}
}
