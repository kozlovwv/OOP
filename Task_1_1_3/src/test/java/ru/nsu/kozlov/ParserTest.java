package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    void testParse1() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Expression ex = Parser.parse("(5 + 6-123) * 10 / 15 + 4 - (2 * x)");
        ex.print();

        String consoleOutput = outputStream.toString();

        assertEquals("((((((5+6)-123)*10)/15)+4)-(2*x))", consoleOutput);
        assertEquals(-74.66666666666667, ex.eval("y=10; z= 123; x = 2"));

        System.setOut(System.out);
    }

    @Test
    void testParse2() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Expression ex = Parser.parse("1 + 1 + 1 + (((((2 * 8)/2))))");
        ex.print();

        String consoleOutput = outputStream.toString();

        assertEquals("(((1+1)+1)+((2*8)/2))", consoleOutput);
        assertEquals(11.0, ex.eval("x = 2; y = 5; z = 10"));

        System.setOut(System.out);
    }
}
