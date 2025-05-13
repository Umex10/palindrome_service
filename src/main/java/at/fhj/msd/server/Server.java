package at.fhj.msd.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    int pt;
    int count;

    public Server(int pt) {
        if (pt < 1023 || pt > 65535) {
            throw new IllegalArgumentException("The pt Number is invalid!");
        }

        this.pt = pt;
        this.count = 0;
    }

    public void listen() {

        while (true) { 
    
        try (ServerSocket server = new ServerSocket(this.pt); 
        Socket socket = server.accept(); 
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.printf("client #%d connected...\n", ++this.count);
            out.println("Hello my fellow socket, welcome to Lindrome Tester 5001!");
            out.println("Lets start... Give me a Lindrome!");

            String message;
            while((message = in.readLine()) != null) {

                out.println(checkLindrome(message));
                

            }


            

        } catch (IOException e) {
            e.printStackTrace();
        }
           }
    }

    public String checkLindrome(String message) {

        char[] letters = message.toCharArray();
        char[] lettersReversed = new char[letters.length];

        int count = 0;

        for (int i = letters.length -1; i >= 0; i--) {

            lettersReversed[count++] = letters[i];

        }

        String reversed = new String(lettersReversed);

        if (!message.equals(reversed)) {
            return "The given word is not a lindrome!";
        }

        return "LINDROME!";
    }

}
