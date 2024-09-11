package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    void sampleTest() {
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, Main.heapsort(new int[] {5, 4, 3, 2, 1}));
    }

    @Test
    void testWithNegativeData() {
        int[] arr = new int[] {88, -16, -100, 58, -91, 38, -83, -2, -81, -4,
                               -13, -69, 48, 93, -34, -56, -51, -36, 84, 59};
        assertArrayEquals(arr, Main.heapsort(arr));
    }

    @Test
    void testWithBigData() {
        int[] arr = new int[] {1384281605, -321243027, 2147483647, 1484361240, -2147483648,
                               277482762, 368116870, -1017886818, -48718318, 395457313,
                               -1635347863, -1283490443, 693398004, 1620437001, -167835604,
                               1370687969, -178158945, -1172207360, 1564864226, -203970097};
        assertArrayEquals(arr, Main.heapsort(arr));
    }

    @Test
    void testWithSortedArray() {
        int[] arr = new int[] {-97, -79, -79, -75, -72, -71, -12, -10, -6,
                               -3, 1, 36, 39, 47, 50, 63, 71, 79, 82, 90};
        assertArrayEquals(arr, Main.heapsort(arr));
    }

    @Test
    void testForMain() {
        Main.main(null);
        assertTrue(true);
    }
}