package ru.nsu.kozlov;

public class Prime {
    public static boolean isNotPrime(Integer num) {
        for (int divider = 2; divider < num; divider++) {
            if ((num % divider) == 0) {
                return true;
            }
        }
        return false;
    }
}
