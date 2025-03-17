package ru.nsu.kozlov;

/**
 * Timer.
 */
public class MyTimer {
    public static long startTime;

    public static double getTime() {
        return (((double) (System.currentTimeMillis() - startTime)) / 1000.0);
    }
}
