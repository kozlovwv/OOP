package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    void testPlay() {
        ByteArrayInputStream inContent = new ByteArrayInputStream("0\n".repeat(100).getBytes());
        System.setIn(inContent);

        Game.play(100);

        ByteArrayInputStream inContent2 = new ByteArrayInputStream("1\n".repeat(1000).getBytes());
        System.setIn(inContent2);

        Game.play(100);

        assertTrue(true);
    }

    @Test
    void testPrintScores() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        int plS = 10;
        int dlS = 5;
        Game.printScores(plS, dlS);
        String consoleOutput = outputStream.toString();
        assertEquals("Счет " + plS + ":" + dlS + " в вашу пользу\n", consoleOutput);

        System.setOut(System.out);
    }

    @Test
    void testPrintScores2() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        int plS = 20;
        int dlS = 20;
        Game.printScores(plS, dlS);
        String consoleOutput = outputStream.toString();
        assertEquals("Счет " + plS + ":" + dlS + "\n", consoleOutput);

        System.setOut(System.out);
    }

    @Test
    void testPrintScores3() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        int plS = 10;
        int dlS = 20;
        Game.printScores(plS, dlS);
        String consoleOutput = outputStream.toString();
        assertEquals("Счет " + plS + ":" + dlS + " в пользу дилера\n", consoleOutput);

        System.setOut(System.out);
    }
}