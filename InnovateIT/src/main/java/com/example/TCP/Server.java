package com.example.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private static final int PORT = 9092;
    private static List<PrintWriter> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
//            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
//                System.out.println("New client connected: " + clientSocket);

//                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                clients.add(out);

                Thread clientHandler = new Thread(new ClientHandler(clientSocket));
                clientHandler.start();

//                String userInputLine;
//                while ((userInputLine = userInput.readLine()) != null) {
//                    out.println(userInputLine);
//                    if (userInputLine.equalsIgnoreCase("quit")
//                            || userInputLine.equalsIgnoreCase("exit")
//                            || userInputLine.equalsIgnoreCase("bye")) {
//                        break;
//                    }
//                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Message received from client: " + inputLine);
                    broadcast(inputLine);
                    if (inputLine.equalsIgnoreCase("bye")
                            || inputLine.equalsIgnoreCase("exit")
                            || inputLine.equalsIgnoreCase("quit")
                    ) {
                        System.out.println("Client disconnected: " + clientSocket);
                        clients.remove(in);
                        clientSocket.close();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void broadcast(String message) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            for (PrintWriter client : clients) {
                client.println(formattedDateTime + " " + message);
            }
        }
    }
}