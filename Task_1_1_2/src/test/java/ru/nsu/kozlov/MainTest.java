package ru.nsu.kozlov;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MainTest {

//    @Test
//    void testForMain() {
//
//    }

    @Test
    void testPrintScores() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        int plS = 10;
        int dlS = 5;
        Main.printScores(plS, dlS);

        String consoleOutput = outputStream.toString();
        assertEquals("Счет " + plS + ":" + dlS + " в вашу пользу\n", consoleOutput);
        System.setOut(System.out);
    }
}