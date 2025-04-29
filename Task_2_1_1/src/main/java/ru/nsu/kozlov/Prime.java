package ru.nsu.kozlov;

/**
 * Class for defining whether number is compound.
 */
public class Prime {
    /**
     * @param num number.
     * @return true - if number is compound, false - otherwise.
     */
    public static boolean isNotPrime(Integer num) {
        int numSqrt = (int) Math.sqrt((double) num) + 1;
        for (int divider = 2; divider < numSqrt; divider++) {
            if ((num % divider) == 0) {
                return true;
            }
        }
        return false;
    }
}
