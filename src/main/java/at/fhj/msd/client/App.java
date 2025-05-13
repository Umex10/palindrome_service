package at.fhj.msd.client;

public class App {
    

    public static void main(String[] args) {
         Client client = new Client("localhost", 1234);

        client.ask();

    }

   
}
