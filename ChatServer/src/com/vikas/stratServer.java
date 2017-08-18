package com.vikas;

public class stratServer {
    public static void main(String[] args) {
      int port=8817;
      Server server=new Server(port);
      server.start();
    }
}
