package hw_7;

import java.net.ServerSocket;
import java.net.Socket;

public class PooledEchoServer {
    private final int clientCapacity;
    private final int port;
    private ServerSocket requestSocket;

    public PooledEchoServer(int port, int clientCapacity) {
        this.port = port;
        this.clientCapacity = clientCapacity;
    }

    public static void main(String[] args) {
        PooledEchoServer server = new PooledEchoServer(2002, 2);
        server.setUpHandlers();
        server.acceptConnections();
    }

    private void setUpHandlers() {
        for (int i = 0; i < clientCapacity; i++) {
            PooledConnectionHandler handler = new PooledConnectionHandler();
            new Thread(handler, "Thread #" + i).start();
        }
    }

    public void acceptConnections() {
        try {
            ServerSocket server = new ServerSocket(port, 5);
            Socket client = null;

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
        PooledConnectionHandler.processRequest(client);
    }
}
