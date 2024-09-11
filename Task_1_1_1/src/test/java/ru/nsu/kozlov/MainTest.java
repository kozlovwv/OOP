package ru.nsu.kozlov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void SampleTest() {
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, Main.heapsort(new int[] {5, 4, 3, 2, 1}));
    }

    @Test
    void TestWithNegativeData() {
        int[] arr = new int[] {88, -16, -100, 58, -91, 38, -83, -2, -81, -4, -13, -69, 48, 93, -34, -56, -51, -36, 84, 59};
        assertArrayEquals(arr, Main.heapsort(arr));
    }

    @Test
    void TestWithBigData() {
        int[] arr = new int[] {1384281605, -321243027, 259560201, 1484361240, -1889215991, 277482762, 368116870, -1017886818, -48718318, 395457313, -1635347863, -1283490443, 693398004, 1620437001, -167835604, 1370687969, -178158945, -1172207360, 1564864226, -203970097};
        assertArrayEquals(arr, Main.heapsort(arr));
    }

}