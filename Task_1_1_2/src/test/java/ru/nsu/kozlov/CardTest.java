package ru.nsu.kozlov;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    @Test
    void testPrintCard() {
        Card card = new Card("Пики", "Дама", 10);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        card.print();

        String consoleOutput = outputStream.toString();

        assertEquals("Дама Пики (10)", consoleOutput);

        System.setOut(System.out);
    }
}