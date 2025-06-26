package ru.nsu.kozlov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Worker's connection.
 */
public class WorkerHandler {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    /**
     * connection
     * @param socket socket
     */
    public WorkerHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * sending task.
     * @param number number
     * @return is number composite
     */
    public boolean sendTask(int number) throws IOException {
        out.println("TASK:" + number);
        String response = in.readLine();
        if (response != null && response.startsWith("RESULT:")) {
            boolean isPrime = Boolean.parseBoolean(response.split(":")[1]);
            return !isPrime;
        }
        throw new IOException("Invalid response");
    }

    /**
     * shutdown connection.
     */
    public void shutdown() {
        try {
            out.println("SHUTDOWN");
            socket.close();
        } catch (IOException ignored) {
            System.err.println("...");
        }
    }

    public boolean isAlive() {
        return !socket.isClosed() && socket.isConnected();
    }
}
