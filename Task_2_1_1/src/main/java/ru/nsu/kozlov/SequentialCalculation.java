package ru.nsu.kozlov;

import java.util.List;

public class SequentialCalculation implements FindCompositeNumber{
    public static boolean hasCompositeNumber(List<Integer> numbers) {
        for (int number : numbers) {
            if (Prime.isNotPrime(number)) {
                return true;
            }
        }
        return false;
    }
}
