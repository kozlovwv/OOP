package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

/** tests for class Game.
 */
public class GameTest {

    @Test
    void testOfGameplayWithConditionsChecking() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Game game = new Game(new Card[]{new Card("Пики", "Король", 10),
                                        new Card("Пики", "Туз", 11),
                                        new Card("Пики", "Валет", 10),
                                        new Card("Пики", "Дама", 10),
                                        new Card("Пики", "Двойка", 2),
                                        new Card("Пики", "Тройка", 3)});
        game.play(1);

        String consoleOutput = outputStream.toString();

        assertEquals("Добро пожаловать в Блэкджек!\n"
                            + "\n"
                            + "Раунд 1\n"
                            + "\tВаши карты: [Король Пики (10), Туз Пики (11)] --> 21\n"
                            + "\tКарты диллера: [Валет Пики (10), <закрытая карта>]\n"
                            + "\n"
                            + "Ход дилера\n"
                            + "-------\n"
                            + "Дилер открывает закрытую карту Дама Пики (10)\n"
                            + "\tВаши карты: [Король Пики (10), Туз Пики (11)] --> 21\n"
                            + "\tКарты диллера: [Валет Пики (10), Дама Пики (10)] --> 20\n"
                            + "Вы выиграли раунд!\n"
                            + "Счет 1:0 в вашу пользу\n", consoleOutput);

        assertEquals(game.dealerScore, 0);
        assertEquals(game.playerScore, 1);
        assertEquals(game.roundsCounter, 1);
    }

    @Test
    void testOfGameplayWithConditionsChecking2() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ByteArrayInputStream inContent = new ByteArrayInputStream("0\n".getBytes());
        System.setIn(inContent);

        Game game = new Game(new Card[]{new Card("Пики", "Король", 10),
                                        new Card("Пики", "Тройка", 3),
                                        new Card("Пики", "Туз", 11),
                                        new Card("Пики", "Пятерка", 5),
                                        new Card("Черви", "Пятерка", 5),
                                        new Card("Пики", "Двойка", 2)});

        game.play(1);

        String consoleOutput = outputStream.toString();

        assertEquals("Добро пожаловать в Блэкджек!\n"
                            + "\n"
                            + "Раунд 1\n"
                            + "\tВаши карты: [Король Пики (10), Тройка Пики (3)] --> 13\n"
                            + "\tКарты диллера: [Туз Пики (11), <закрытая карта>]\n"
                            + "\n"
                            + "Ваш ход\n"
                            + "-------\n"
                            + "Введите “1”, чтобы взять карту, и “0”, чтобы остановиться .\n"
                            + "\n"
                            + "Ход дилера\n"
                            + "-------\n"
                            + "Дилер открывает закрытую карту Пятерка Пики (5)\n"
                            + "\tВаши карты: [Король Пики (10), Тройка Пики (3)] --> 13\n"
                            + "\tКарты диллера: [Туз Пики (11), Пятерка Пики (5)] --> 16\n"
                            + "\n"
                            + "Дилер открывает карту Пятерка Черви (5)\n"
                            + "\tВаши карты: [Король Пики (10), Тройка Пики (3)] --> 13\n"
                            + "\tКарты диллера: [Туз Пики (11), Пятерка Пики (5), Пятерка Черви (5)] --> 21\n"
                            + "Вы проиграли раунд!\n"
                            + "Счет 0:1 в пользу дилера\n", consoleOutput);

        assertEquals(game.dealerScore, 1);
        assertEquals(game.playerScore, 0);
        assertEquals(game.roundsCounter, 1);
    }

    @Test
    void testPlay() {
        ByteArrayInputStream inContent = new ByteArrayInputStream("0\n".repeat(100).getBytes());
        System.setIn(inContent);

        Game game = new Game();
        game.play(100);

        ByteArrayInputStream inContent2 = new ByteArrayInputStream("1\n".repeat(1000).getBytes());
        System.setIn(inContent2);

        game = new Game();
        game.play(100);

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