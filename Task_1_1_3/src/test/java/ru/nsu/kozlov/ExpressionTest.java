package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ExpressionTest {
    @Test
    void testPrint() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Expression ex = Parser.parse("1+2/3*5-6 + (123*8/3-7+(4/x + y))");
        ex.print();

        String consoleOutput = outputStream.toString();

        assertEquals("(((1+((2/3)*5))-6)+((((123*8)/3)-7)+((4/x)+y)))", consoleOutput);

        System.setOut(System.out);
    }

    @Test
    void testDerivative() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Expression ex = Parser.parse("1 + 2*x");
        Expression exD = ex.derivative("x");
        exD.print();

        String consoleOutput = outputStream.toString();

        assertEquals("(0+((0*x)+(2*1)))", consoleOutput);

        System.setOut(System.out);

    }

    @Test
    void testDerivative2() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Expression ex = Parser.parse("x - 5/x");
        Expression exD = ex.derivative("x");
        exD.print();

        String consoleOutput = outputStream.toString();

        assertEquals("(1-(((1*5)-(x*0))/(x*x)))", consoleOutput);

        System.setOut(System.out);
    }

    @Test
    void testEval() {
        Expression ex = Parser.parse("1+2/3*5-6 + (123*8/3-7+(4/x + y))");
        double res = ex.eval("x = 5; y = 4");

        assertEquals(324.1333333333333, res);
    }

    @Test
    void testEval2() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Expression ex = Parser.parse("1+2/3*5-6 + (123*8/3-7+(4/x + y))");
        try {
            double res = ex.eval("x = 5; z = 4");
        } catch (ArithmeticException e) {
            System.out.println("Var Error");
        }

        String consoleOutput = outputStream.toString();

        assertEquals("Var Error\n", consoleOutput);

        System.setOut(System.out);
    }

    @Test
    void testEval3() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Expression ex = Parser.parse("1+2/0*5-6 + (123*8/3-7+(4/x + y))");
        try {
            double res = ex.eval("x = 5; y = 4");
        } catch (IllegalStateException e) {
            System.out.println("Division by 0");
        }

        String consoleOutput = outputStream.toString();

        assertEquals("Division by 0\n", consoleOutput);

        System.setOut(System.out);
    }

    @Test
    void testSimplify() {
        Expression ex = Parser.parse("1+2/4*5-6 + (123*8/3-7+(4/5 + 4))");
        Expression exS = ex.simplify();
        assertEquals(exS, new Number(323.3));
    }

    @Test
    void testSimplify2() {
        Expression ex = Parser.parse("((1+x) - (x+1))");
        Expression exS = ex.simplify();
        assertEquals(exS, new Number(0));
    }

    @Test
    void testSimplify3() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Expression ex = Parser.parse("z+2/4*5-6 + (x*8/3-7+(4/y + 4))");
        Expression exS = ex.simplify();
        exS.print();

        String consoleOutput = outputStream.toString();

        assertEquals("(((z+2.5)-6)+((((x*8)/3)-7)+((4/y)+4)))", consoleOutput);

        System.setOut(System.out);
    }
}