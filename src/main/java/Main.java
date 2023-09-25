import httppackage.HttpServer;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer httpServer = new HttpServer();

        httpServer.start();
    }
}