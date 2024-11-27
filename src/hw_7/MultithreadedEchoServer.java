package hw_7;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedEchoServer {
    private final int port;

    public MultithreadedEchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        MultithreadedEchoServer server = new MultithreadedEchoServer(2002);
        server.acceptConnection();
    }

    public void acceptConnection() {
        try {
            ServerSocket server = new ServerSocket(port, 50);
            Socket client;

            while (true) {
                client = server.accept();
                System.out.println("Accepted connection from " + client.getRemoteSocketAddress() + ".");
                handleConnection(client);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleConnection(Socket client) {
        new Thread(new ConnectionHandler(client)).start();
    }
}
