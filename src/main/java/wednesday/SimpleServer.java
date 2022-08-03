package wednesday;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class SimpleServer {

    public static void main(String[] args) throws Exception {
        System.out.println("Server running at http://localhost:8000");
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        SimpleServer ss = new SimpleServer();
        HtmlHanderler index = ss.new HtmlHanderler("src/main/java/Index.html");
        HtmlHanderler about = ss.new HtmlHanderler("src/main/java/About.html");
        HtmlHanderler contact = ss.new HtmlHanderler("src/main/java/ContactUs.html");
        server.createContext("/index", index);
        server.createContext("/about", about);
        server.createContext("/contact", contact);
        server.setExecutor(null);
        server.start();
    }

    class HtmlHanderler implements HttpHandler {
        String path;
        public HtmlHanderler(String path) {
            this.path = path;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            StringBuilder contentBuilder = new StringBuilder();
            try {
                BufferedReader in = new BufferedReader(new FileReader(path));
                String str;
                while ((str = in.readLine()) != null) {
                    contentBuilder.append(str);
                }
                String content = contentBuilder.toString();
                exchange.sendResponseHeaders(200, content.length());
                OutputStream os = exchange.getResponseBody();
                os.write(content.getBytes());
                in.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

