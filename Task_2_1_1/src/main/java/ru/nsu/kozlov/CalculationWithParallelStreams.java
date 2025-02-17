package ru.nsu.kozlov;

import java.util.List;

/**
 * Calculation with parallelStream.
 */
public class CalculationWithParallelStreams implements FindCompositeNumber {
    public static boolean hasCompositeNumber(List<Integer> numbers) {
        return numbers.parallelStream().anyMatch(Prime::isNotPrime);
    }
}
