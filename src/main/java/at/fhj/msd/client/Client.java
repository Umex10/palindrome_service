package at.fhj.msd.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import at.fhj.msd.LindromeReply;
import at.fhj.msd.LindromeRequest;

public class Client {

    String host;
    int pt;

    public Client(String host, int pt) {

        if (host == null || host.isEmpty()) {
            throw new IllegalArgumentException("invalid host");
        }
        if (pt < 1024 || pt > 65535) {
            throw new IllegalArgumentException("invalid port");
        }
        this.host = host;
        this.pt = pt;

    }

    public String ask() {

        try (Socket socket = new Socket(this.host, this.pt); 
       // PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
       // BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
        Scanner scanner = new Scanner(System.in); 
        ObjectOutputStream outObject = new ObjectOutputStream(socket.getOutputStream()); 
        ObjectInputStream inObject = new ObjectInputStream(socket.getInputStream());) {

            String line1 = (String) inObject.readObject();//Read Introduction
            String line2 = (String)   inObject.readObject();

            System.out.println(line1);
            System.out.println(line2);

            while (true) {
                System.out.print("Pls enter a word: ");
                String message = scanner.next();

                // Anfrage senden
                outObject.writeObject(new LindromeRequest(message));

                // Antwort empfangen
                LindromeReply reply = (LindromeReply) inObject.readObject();
                System.out.println("Server replied: " + reply.getMessage());

                System.out.print("Do you want to quit? (y/n): ");
                String answer = scanner.next();
                if (answer.equalsIgnoreCase("y")) {
                    outObject.writeObject(answer);
                    String down = (String) inObject.readObject();
                    System.out.println(down);
                    break;
                }

                outObject.writeObject("Continue... Lets go!");
                
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
