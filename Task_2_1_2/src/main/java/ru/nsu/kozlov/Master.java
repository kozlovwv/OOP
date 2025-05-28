package ru.nsu.kozlov;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Server implementation.
 */
public class Master {
    private static final int PORT = 11111;
    private static final int TIMEOUT_MS = 2000;
    private static final int MAX_RETRIES = 3;
    private static final int[] INPUT = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private static final Queue<WorkerHandler> workers = new ConcurrentLinkedQueue<>();
    private static final ExecutorService taskPool = Executors.newCachedThreadPool();

    /**
     * starting server.
     * @param args args
     */
    public static void main(String[] args) {
        startWorkerListener();
        waitForAtLeastOneWorker();
        try {
            boolean result = distributeTasks();
            System.out.println("At least one number is not prime: " + result);
        } finally {
            shutdownAll();
        }
    }

    private static void startWorkerListener() {
        Thread acceptor = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Listening for workers...");
                while (true) {
                    Socket socket = serverSocket.accept();
                    socket.setSoTimeout(TIMEOUT_MS);
                    WorkerHandler handler = new WorkerHandler(socket);
                    workers.add(handler);
                    System.out.println("New worker connected: " + socket.getRemoteSocketAddress());
                }
            } catch (IOException e) {
                System.err.println("Worker listener error: " + e.getMessage());
            }
        });
        acceptor.setDaemon(true);
        acceptor.start();
    }

    private static void waitForAtLeastOneWorker() {
        System.out.println("Waiting for at least one worker to connect...");
        while (workers.isEmpty()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
                System.err.println("...");
            }
        }
        System.out.println("At least one worker is connected. Starting task distribution...");
    }

    private static boolean distributeTasks() {
        List<Future<Boolean>> futures = new ArrayList<>();

        for (int number : Master.INPUT) {
            futures.add(taskPool.submit(() -> tryTaskWithRetries(number)));
        }

        try {
            for (Future<Boolean> future : futures) {
                if (future.get()) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("Task execution error: " + e.getMessage());
        }

        return false;
    }

    private static boolean tryTaskWithRetries(int number) {
        for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
            for (WorkerHandler worker : workers) {
                if (!worker.isAlive()) {
                    continue;
                }
                try {
                    return worker.sendTask(number);
                } catch (IOException e) {
                    System.err.println("Task failed on worker: " + e.getMessage());
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
                System.err.println("...");
            }
        }

        System.err.println("Failed to process number after retries: " + number);
        return false;
    }

    private static void shutdownAll() {
        for (WorkerHandler worker : workers) {
            worker.shutdown();
        }
        taskPool.shutdown();
        System.out.println("All workers shut down.");
    }
}
