package pl.warcaby;

import pl.warcaby.Server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(8080);
        server.start();
    }
}