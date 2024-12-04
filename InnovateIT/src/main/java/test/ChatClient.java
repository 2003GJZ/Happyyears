package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 9000;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to chat server");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Thread messageReceiver = new Thread(() -> {
                try {
                    String serverResponse;
                    while ((serverResponse = serverInput.readLine()) != null) {
                        System.out.println("Server: " + serverResponse);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            messageReceiver.start();

            String userInputLine;
            while ((userInputLine = userInput.readLine()) != null) {
                out.println(userInputLine);
                if (userInputLine.equalsIgnoreCase("quit")
                        || userInputLine.equalsIgnoreCase("exit")
                        || userInputLine.equalsIgnoreCase("quit")) {
                    break;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}