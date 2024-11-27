package hw_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleEchoClient {
    private final String ipAddress;
    private final int port;
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;

    SimpleEchoClient(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public static void main(String[] args) throws IOException {
        SimpleEchoClient client = new SimpleEchoClient("localhost", 2002);
        client.setUpConnection();

        boolean result = client.talkToServer();
        while (result) {
            result = client.talkToServer();
        }

        client.removeConnection();
        System.out.println("Client shut down.");
    }

    public void setUpConnection() {
        try {
            socket = new Socket(ipAddress, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeConnection() {
        try {
            reader.close();
            writer.close();
            socket.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean talkToServer() {
        try {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            if (line.strip().equalsIgnoreCase("end")) {
                return false;
            }
            writer.println(line);

            String response = reader.readLine();
            if (response == null) {
                throw new RuntimeException("Connection to server lost!");
            }
            System.out.println(response);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
