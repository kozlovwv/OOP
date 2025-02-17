package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PrimeNumbersTests {
    @Test
    void testWithCompoundNumberInBeginningBig() {
        List<Integer> numbers = new ArrayList<Integer>();
        String filePath = "src/test/resources/testBigComp.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    numbers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка при преобразовании строки в число: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        assertTrue(SequentialCalculation.hasCompositeNumber(numbers));
        long end = System.currentTimeMillis();
        System.out.println("BigComp SequentialCalculation: " + (end - start));

        start = System.currentTimeMillis();
        assertTrue(ParallelCalculationWithThreads.hasCompositeNumber(numbers));
        end = System.currentTimeMillis();
        System.out.println("BigComp ParallelCalculationWithThreads: " + (end - start));

        start = System.currentTimeMillis();
        assertTrue(CalculationWithParallelStreams.hasCompositeNumber(numbers));
        end = System.currentTimeMillis();
        System.out.println("BigComp CalculationWithParallelStreams: " + (end - start));
    }

    @Test
    void testWithCompoundNumberInBeginningUltraBig() {
        List<Integer> numbers = new ArrayList<Integer>();
        String filePath = "src/test/resources/testUltraBigComp.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    numbers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка при преобразовании строки в число: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        assertTrue(SequentialCalculation.hasCompositeNumber(numbers));
        long end = System.currentTimeMillis();
        System.out.println("UltraBigComp SequentialCalculation: " + (end - start));

        start = System.currentTimeMillis();
        assertTrue(ParallelCalculationWithThreads.hasCompositeNumber(numbers));
        end = System.currentTimeMillis();
        System.out.println("UltraBigComp ParallelCalculationWithThreads: " + (end - start));

        start = System.currentTimeMillis();
        assertTrue(CalculationWithParallelStreams.hasCompositeNumber(numbers));
        end = System.currentTimeMillis();
        System.out.println("UltraBigComp CalculationWithParallelStreams: " + (end - start));
    }

    @Test
    void testSmall() {
        List<Integer> numbers = new ArrayList<Integer>();
        String filePath = "src/test/resources/testSmall.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    numbers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка при преобразовании строки в число: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        assertTrue(SequentialCalculation.hasCompositeNumber(numbers));
        long end = System.currentTimeMillis();
        System.out.println("Small SequentialCalculation: " + (end - start));

        start = System.currentTimeMillis();
        assertTrue(ParallelCalculationWithThreads.hasCompositeNumber(numbers));
        end = System.currentTimeMillis();
        System.out.println("Small ParallelCalculationWithThreads: " + (end - start));

        start = System.currentTimeMillis();
        assertTrue(CalculationWithParallelStreams.hasCompositeNumber(numbers));
        end = System.currentTimeMillis();
        System.out.println("Small CalculationWithParallelStreams: " + (end - start));
    }

    @Test
    void testSmallMiddle() {
        List<Integer> numbers = new ArrayList<Integer>();
        String filePath = "src/test/resources/testSmallMiddle.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    numbers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка при преобразовании строки в число: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        assertTrue(SequentialCalculation.hasCompositeNumber(numbers));
        long end = System.currentTimeMillis();
        System.out.println("SmallMiddle SequentialCalculation: " + (end - start));

        start = System.currentTimeMillis();
        assertTrue(ParallelCalculationWithThreads.hasCompositeNumber(numbers));
        end = System.currentTimeMillis();
        System.out.println("SmallMiddle ParallelCalculationWithThreads: " + (end - start));

        start = System.currentTimeMillis();
        assertTrue(CalculationWithParallelStreams.hasCompositeNumber(numbers));
        end = System.currentTimeMillis();
        System.out.println("SmallMiddle CalculationWithParallelStreams: " + (end - start));
    }

    @Test
    void testMiddle() {
        List<Integer> numbers = new ArrayList<Integer>();
        String filePath = "src/test/resources/testMiddle.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    numbers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка при преобразовании строки в число: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        assertTrue(SequentialCalculation.hasCompositeNumber(numbers));
        long end = System.currentTimeMillis();
        System.out.println("Middle SequentialCalculation: " + (end - start));

        start = System.currentTimeMillis();
        assertTrue(ParallelCalculationWithThreads.hasCompositeNumber(numbers));
        end = System.currentTimeMillis();
        System.out.println("Middle ParallelCalculationWithThreads: " + (end - start));

        start = System.currentTimeMillis();
        assertTrue(CalculationWithParallelStreams.hasCompositeNumber(numbers));
        end = System.currentTimeMillis();
        System.out.println("Middle CalculationWithParallelStreams: " + (end - start));
    }

    @Test
    void testBig() {
        List<Integer> numbers = new ArrayList<Integer>();
        String filePath = "src/test/resources/testBig.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    numbers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка при преобразовании строки в число: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        assertTrue(SequentialCalculation.hasCompositeNumber(numbers));
        long end = System.currentTimeMillis();
        System.out.println("Big SequentialCalculation: " + (end - start));

        start = System.currentTimeMillis();
        assertTrue(ParallelCalculationWithThreads.hasCompositeNumber(numbers));
        end = System.currentTimeMillis();
        System.out.println("Big ParallelCalculationWithThreads: " + (end - start));

        start = System.currentTimeMillis();
        assertTrue(CalculationWithParallelStreams.hasCompositeNumber(numbers));
        end = System.currentTimeMillis();
        System.out.println("Big CalculationWithParallelStreams: " + (end - start));
    }

    @Test
    void testUltraBig() {
        List<Integer> numbers = new ArrayList<Integer>();
        String filePath = "src/test/resources/testUltraBig.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    numbers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка при преобразовании строки в число: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        assertTrue(SequentialCalculation.hasCompositeNumber(numbers));
        long end = System.currentTimeMillis();
        System.out.println("UltraBig SequentialCalculation: " + (end - start));

        start = System.currentTimeMillis();
        assertTrue(ParallelCalculationWithThreads.hasCompositeNumber(numbers));
        end = System.currentTimeMillis();
        System.out.println("UltraBig ParallelCalculationWithThreads: " + (end - start));

        start = System.currentTimeMillis();
        assertTrue(CalculationWithParallelStreams.hasCompositeNumber(numbers));
        end = System.currentTimeMillis();
        System.out.println("UltraBig CalculationWithParallelStreams: " + (end - start));
    }

    @Test
    void testInterface() {
        assertFalse(FindCompositeNumber.hasCompositeNumber(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    void testOnlyPrime() {
        assertFalse(SequentialCalculation.hasCompositeNumber(Arrays.asList(2, 3, 5, 7, 11)));
    }

    @Test
    void testThreadsMoreThanSize() {
        ParallelCalculationWithThreads.numberThreads = 10000;
        assertFalse(ParallelCalculationWithThreads.hasCompositeNumber(Arrays.asList(2, 3, 5, 7, 11)));
    }
}