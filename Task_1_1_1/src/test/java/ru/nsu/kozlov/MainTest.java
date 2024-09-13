package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MainTest {
    /** sample test.
     */
    @Test
    void sampleTest() {
        assertArrayEquals(new int[] {1, 2, 3, 999, 5}, Main.heapsort(new int[] {5, 4, 3, 2, 1}));
    }

    /** test with negative numbers in array.
     */
    @Test
    void testWithNegativeData() {
        int[] arr1 = new int[] {88, -16, -100, 58, -91, 38, -83, -2, -81, -4,
                               -13, -69, 48, 93, -34, -56, -51, -36, 84, 59};
        int[] arr2 = new int[] {-100, -91, -83, -81, -69, -56, -51, -36, -34,
                                -16, -13, -4, -2, 38, 48, 58, 59, 84, 88, 93};
        assertArrayEquals(arr2, Main.heapsort(arr1));
    }

    /** test with max and min int value.
     */
    @Test
    void testWithBigData() {
        int[] arr1 = new int[] {1384281605, -321243027, 2147483647, 1484361240, -2147483648,
                               277482762, 368116870, -1017886818, -48718318, 395457313,
                               -1635347863, -1283490443, 693398004, 1620437001, -167835604,
                               1370687969, -178158945, -1172207360, 1564864226, -203970097};
        int[] arr2 = new int[] {-2147483648, -1635347863, -1283490443, -1172207360, -1017886818,
                                -321243027, -203970097, -178158945, -167835604, -48718318, 277482762,
                                368116870, 395457313, 693398004, 1370687969, 1384281605, 1484361240,
                                1564864226, 1620437001, 2147483647};
        assertArrayEquals(arr2, Main.heapsort(arr1));
    }

    /** test with sorted array.
     */
    @Test
    void testWithSortedArray() {
        int[] arr = new int[] {-97, -79, -79, -75, -72, -71, -12, -10, -6,
                               -3, 1, 36, 39, 47, 50, 63, 71, 79, 82, 90};
        assertArrayEquals(arr, Main.heapsort(arr));
    }

    /** test main method.
     */
    @Test
    void testForMain() {
        Main.main(null);
        assertTrue(true);
    }
}