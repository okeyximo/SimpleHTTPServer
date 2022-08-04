package wednesday;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class SimpleServer {
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    public SimpleServer() throws IOException {
    }
    public void routing(String context, String path) {
        server.createContext(context, x -> {
            x.sendResponseHeaders(200, 0);
            OutputStream os = x.getResponseBody();
            os.write(Files.readAllBytes(Paths.get(path)));
            os.close();
        });
    }
    public void setExecutor() {
        server.setExecutor(null);
    }

    public void startServer() {
        server.start();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Server running at http://localhost:8000");
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.routing("/", "src/main/java/resources/index.html");
        simpleServer.routing("/about", "src/main/resources/java/about.html");
        simpleServer.routing("/contact", "src/main/resources/java/contactUs.html");
        simpleServer.setExecutor();
        simpleServer.startServer();
    }
}



