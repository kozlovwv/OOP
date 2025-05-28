package ru.nsu.kozlov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client implementation.
 */
public class Worker {
    private static final String HOST = "localhost";
    private static final int PORT = 11111;

    /**
     * client starting.
     * @param args args.
     */
    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("TASK:")) {
                    int number = Integer.parseInt(line.split(":")[1]);
                    boolean result = isPrime(number);
                    out.println("RESULT:" + result);
                } else if (line.equals("SHUTDOWN")) {
                    System.out.println("Shutting down.");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Worker failed to connect or crashed.");
        }
    }

    /**
     * is number prime.
     * @param number number.
     * @return is number prime.
     */
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
