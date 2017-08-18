package com.vikas;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;

public class ServerOperations extends Thread {
    private final Socket clientSocket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ServerOperations(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClientSocketOperations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleClientSocketOperations() throws IOException {
        inputStream = clientSocket.getInputStream();
        outputStream = clientSocket.getOutputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = br.readLine()) != null) {
            String[] tokens = StringUtils.split(line);
            if (tokens.length > 0) {
                String command = tokens[0];
                if (command.equalsIgnoreCase("quit")) {
                    System.out.println("Quit Command Found");
                    break;
                } else if (command.equalsIgnoreCase("login")) {
                    handleLoginOperation(tokens);
                } else {
                    String command_error = "command '" + command + "' not found!!\n";
                    System.out.println(command_error);
                    outputStream.write(command_error.getBytes());
                }
            }
        }
        clientSocket.close();
    }


    private void handleLoginOperation(String[] tokens) throws IOException {
        System.out.println("Login Command");
        if (tokens.length >= 3) {
            String username = tokens[1];
            String password = tokens[2];
            if (username.equalsIgnoreCase("vikas") && password.equalsIgnoreCase("dixit") || username.equalsIgnoreCase("rahul") && password.equalsIgnoreCase("dixit") || username.equalsIgnoreCase("guest") && password.equalsIgnoreCase("guest")) {
                String loginSuccessMsg="Welcome "+username+"\n";
                System.out.println("Login Successful\n"+loginSuccessMsg);
                outputStream.write(loginSuccessMsg.getBytes());
            }else {
                System.out.println("Login Failed ");
                outputStream.write("login failed\n".getBytes());
            }
        }

    }
}