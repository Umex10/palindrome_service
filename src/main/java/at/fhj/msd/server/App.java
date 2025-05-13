package at.fhj.msd.server;

public class App {

    public static void main(String[] args) {
        Server server = new Server(1234);
        server.listen();
    }

}
