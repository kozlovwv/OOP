package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    void testMain() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Main.main(null);

        String consoleOutput = outputStream.toString();

        assertEquals("((1+x)-(1+x))\n0", consoleOutput);
    }
}