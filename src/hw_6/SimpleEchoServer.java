package hw_6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleEchoServer {
    private final int port;

    public SimpleEchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        SimpleEchoServer server = new SimpleEchoServer(2002);
        server.acceptConnection();
    }

    public void acceptConnection() {
        try {
            ServerSocket server = new ServerSocket(port);
            Socket client;

            while (true) {
                client = server.accept();
                System.out.println("Accepted connection from " + client.getRemoteSocketAddress() + ".");
                echo(client);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void echo(Socket client) {
        try {
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            PrintWriter writer = new PrintWriter(out, true);

            String line;
            while ((line = reader.readLine()) != null) {
                writer.println(line);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
        finally {
            try {
                System.out.println("Client " + client.getRemoteSocketAddress() + " closed.");
                client.close();
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
