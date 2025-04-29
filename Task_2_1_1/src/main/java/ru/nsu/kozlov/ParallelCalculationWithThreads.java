package ru.nsu.kozlov;

import java.util.List;

/**
 * Parallel calculation with threads.
 */
public class ParallelCalculationWithThreads implements FindCompositeNumber {
    private static boolean hasComposite;
    public static int numberThreads = 20;

    /**
     * @param numbers - list of integer positive numbers.
     * @return true - if list has a compound number, false - otherwise.
     */
    public static boolean hasCompositeNumber(List<Integer> numbers) {
        hasComposite = false;

        boolean flag = false;
        int lastThreadsValue = numberThreads;
        if (numberThreads > numbers.size()) {
            flag = true;
            numberThreads = numbers.size();
        }

        Thread[] threads = new Thread[numberThreads];

        for (int i = 0; i < numberThreads; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                boolean partialRes = false;

                int elementsPerThread = numbers.size() / numberThreads;
                int startIndex = threadIndex * elementsPerThread;
                int lastIndex = (threadIndex == numberThreads - 1) ? numbers.size() :
                        (threadIndex + 1) * elementsPerThread;

                for (int j = startIndex; j < lastIndex; j++) {
                    partialRes |= Prime.isNotPrime(numbers.get(j));
                }

                synchronized (ParallelCalculationWithThreads.class) {
                    hasComposite |= partialRes;
                }
            });
            threads[i].start();
        }

        /*
        100 els; 8 threads.

        0-th thread -> [0; 11]
        1-th thread -> [12; 23]
        ...
        7-th thread -> [84; 95]
         */

        /*
        10 els; 10 threads.

        0-th thread -> [0]
        1-th thread -> [1]
        ...
        7-th thread -> [7]
        ...
         */

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (flag) {
            numberThreads = lastThreadsValue;
        }

        return hasComposite;
    }
}
