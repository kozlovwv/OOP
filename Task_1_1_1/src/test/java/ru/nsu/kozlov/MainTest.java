package ru.nsu.kozlov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void SampleTest() {
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, Main.heapsort(new int[] {5, 4, 3, 2, 1}));
    }
}