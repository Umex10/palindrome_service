package at.fhj.msd.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

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
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
        Scanner scanner = new Scanner(System.in)) {

            in.readLine();
            in.readLine();

            String message;
            while (true) {

                System.out.println("Pls enter a word: ");
                message = scanner.next();

                out.println(message);

                String reply = in.readLine();
                System.out.println("Server replied: " + reply);

                System.out.println("Do you want to quit? (y/n): ");
                String answer = scanner.next();
                System.out.println();

                if (answer.equalsIgnoreCase("y")) {
                    System.out.println("Tschau Kakao");
                    break;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
