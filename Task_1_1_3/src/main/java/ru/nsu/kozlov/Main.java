package ru.nsu.kozlov;

/** class Main.
 */
public class Main {
    /** main function.
     *
     * @param args arguments from command line.
     */
    public static void main(String[] args) {
        Expression ex = new Sub(new Add(new Number(1), new Variable("x")),
                                new Add(new Number(1), new Variable("x")));
        ex.print();
        System.out.println();

        Expression simple = ex.simplify();
        simple.print();
    }
}