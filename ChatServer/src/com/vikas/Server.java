package com.vikas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {


    private final int serverPort;

    public Server(int port) {
        this.serverPort = port;
    }

    @Override
    public void run() {

        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
           while(true) {
               Socket clientSocket = serverSocket.accept();
               ServerOperations operations = new ServerOperations(clientSocket);
               operations.start();
               // operations.handleClientSocketOperations();
           }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
