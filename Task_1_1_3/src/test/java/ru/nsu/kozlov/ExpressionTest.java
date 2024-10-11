package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testDiv() {
        Expression ex = Parser.parse("10/x");

        assertEquals(ex, new Div(new Number(10), new Variable("x")));

        try {
            double res = ex.eval("x = 0");
        } catch (IllegalStateException exception) {
            assertTrue(true);
        }

        Expression ex2 = Parser.parse("0/x");
        Expression ex2S = ex2.simplify();

        assertEquals(ex2S, new Number(0));

        Expression ex3 = Parser.parse("x/1");
        Expression ex3S = ex3.simplify();

        assertEquals(ex3S, new Variable("x"));
    }

    @Test
    void testMul() {
        Expression ex = Parser.parse("0 * x");

        assertEquals(ex, new Mul(new Number(0), new Variable("x")));

        Expression exS = ex.simplify();
        assertEquals(exS, new Number(0));

        Expression ex2 = Parser.parse("x * 0");
        Expression ex2S = ex2.simplify();
        assertEquals(ex2S, new Number(0));

        Expression ex3 = Parser.parse("1 * x");
        Expression ex3S = ex3.simplify();
        assertEquals(ex3S, new Variable("x"));

        Expression ex4 = Parser.parse("x * 1");
        Expression ex4S = ex4.simplify();
        assertEquals(ex4S, new Variable("x"));
    }

    @Test
    void testSub() {
        Expression ex = Parser.parse("x - x");

        assertEquals(ex, new Sub(new Variable("x"), new Variable("x")));

        Expression exS = ex.simplify();
        assertEquals(exS, new Number(0));

        Expression ex2 = Parser.parse("x - 0");
        Expression ex2S = ex2.simplify();
        assertEquals(ex2S, new Variable("x"));
    }
}