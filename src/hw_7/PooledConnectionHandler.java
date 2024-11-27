package hw_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class PooledConnectionHandler implements Runnable {
    private Socket connection;
    private static final List<Socket> pool = new LinkedList();

    public PooledConnectionHandler() {}

    public void handleConnection() {
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

    public static void processRequest(Socket request) {
        synchronized (pool) {
            pool.add(pool.size(), request);
            pool.notifyAll();
        }
    }

    public void run() {
        while (true) {
            synchronized (pool) {
                while (pool.isEmpty()) {
                    try {
                        pool.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                connection = pool.removeFirst();
            }
            handleConnection();
        }
    }
}
