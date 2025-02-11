package ru.nsu.kozlov;

import java.util.List;

public class CalculationWithParallelStreams implements FindCompositeNumber{
    public static boolean hasCompositeNumber(List<Integer> numbers) {
        return numbers.parallelStream().anyMatch(PrimeNumbers::isNotPrime);
    }
}
