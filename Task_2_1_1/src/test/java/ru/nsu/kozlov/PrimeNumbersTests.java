package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class PrimeNumbersTests {
    @Test
    void sampleTest() {
        assertTrue(SequentialCalculation.hasCompositeNumber(Arrays.asList(1, 2, 3, 10)));
    }

    @Test
    void sampleTest2() {
        ParallelCalculationWithThreads.numberThreads = 10;
        assertTrue(PrimeNumbers.parallelCalculationWithParallelStreams(Arrays.asList(1, 2, 3, 20)));
    }

}