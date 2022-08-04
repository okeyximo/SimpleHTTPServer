package tuesday;

import tuesday.HttpServer;

public class Main {
    public static void main(String[] args) {
        HttpServer server =  new HttpServer(){};
        server.routing();
    }
}
