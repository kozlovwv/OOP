package ru.nsu.kozlov;

import java.util.List;

/**
 * Sequential calculation.
 */
public class SequentialCalculation implements FindCompositeNumber {
    /**
     * @param numbers - list of integer positive numbers.
     * @return true - if list has a compound number, false - otherwise.
     */
    public static boolean hasCompositeNumber(List<Integer> numbers) {
        for (int number : numbers) {
            if (Prime.isNotPrime(number)) {
                return true;
            }
        }
        return false;
    }
}
