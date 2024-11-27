package hw_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
    Socket connection;

    public ConnectionHandler(Socket connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);

            String line;
            while ((line = reader.readLine()) != null) {
                writer.println(line);
            }

            reader.close();
            writer.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                System.out.println("Connection " + connection.getRemoteSocketAddress() + " closed.");
                connection.close();
            }
            catch (Exception e) {
                System.out.println("Error in client " + connection.getRemoteSocketAddress() + ":");
                System.out.println(e.getMessage());
            }
        }
    }
}
