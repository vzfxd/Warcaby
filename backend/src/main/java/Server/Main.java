package Server;

import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        Server server = new Server(8080);
        server.start();
    }
}
