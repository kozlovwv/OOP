package ru.nsu.kozlov;

public class Prime {
    public static boolean isNotPrime(Integer num) {
        int numSqrt = (int) Math.sqrt((double)num) + 1;
        for (int divider = 2; divider < numSqrt; divider++) {
            if ((num % divider) == 0) {
                return true;
            }
        }
        return false;
    }
}
