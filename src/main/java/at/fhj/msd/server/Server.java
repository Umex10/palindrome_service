package at.fhj.msd.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import at.fhj.msd.LindromeReply;
import at.fhj.msd.LindromeRequest;

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
            ObjectOutputStream outObject = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inObject = new ObjectInputStream(socket.getInputStream());) {


                System.out.printf("client #%d connected...\n", ++this.count);
                outObject.writeObject("Hello my fellow socket, welcome to Lindrome Tester 5001!");
                outObject.writeObject("Let's start... Give me a Lindrome!");

                String message;
                boolean status = true;
                while (status) {

                    LindromeRequest request = (LindromeRequest) inObject.readObject();
                    String word = request.getWord();

                    boolean isLindrome = checkLindrome(word);
                    String feedback = isLindrome ? "The given word is a Lindrome!" : "SADDDDDGEEE --> Not a Lindrome";

                    LindromeReply reply = new LindromeReply(isLindrome, feedback);
                    outObject.writeObject(reply);

                    String quit = (String) inObject.readObject();

                    if (quit.equalsIgnoreCase("y")) {
                        outObject.writeObject("Shutting Down....");
                        status = false;
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        }
    }

    public boolean checkLindrome(String message) {

        char[] letters = message.toCharArray();
        char[] lettersReversed = new char[letters.length];

        int count = 0;

        for (int i = letters.length - 1; i >= 0; i--) {

            lettersReversed[count++] = letters[i];

        }

        String reversed = new String(lettersReversed);

        if (!message.equals(reversed)) {
            return false;
        }

        return true;
    }

}
