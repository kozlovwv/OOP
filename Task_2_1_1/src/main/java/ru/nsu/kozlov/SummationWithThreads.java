package ru.nsu.kozlov;

import java.lang.Thread;

public class SummationWithThreads {
    private static int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static int numThreads = 4;
    private static int totalSum = 0;

    public static void main(String[] args) {
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                int partialSum = 0;
                for (int j = threadIndex * (array.length / numThreads); j < (threadIndex + 1) * (array.length / numThreads); j++) {
                    partialSum += array[j];
                }
                synchronized (SummationWithThreads.class) {
                    totalSum += partialSum;
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total sum: " + totalSum);
    }
}
