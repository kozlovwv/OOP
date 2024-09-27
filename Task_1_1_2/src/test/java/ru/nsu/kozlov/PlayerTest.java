package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void testAddCard() {
        Deck deck = new Deck(new Card("Черви", "Валет", 10),
                             new Card("Пики", "Дама", 10));

        Player player = new Player();

        player.addCard(deck);
        player.addCard(deck);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        player.printCards();

        String consoleOutput = outputStream.toString();
        assertEquals("[Валет Черви (10), Дама Пики (10)] --> 20\n", consoleOutput);

        System.setOut(System.out);
    }

    @Test
    void testPrintCards() {
        Deck deck = new Deck(new Card("Черви", "Валет", 10),
                             new Card("Пики", "Дама", 10),
                             new Card("Пики", "Король", 10));

        Player player = new Player();

        player.addCard(deck);
        player.addCard(deck);
        player.addCard(deck);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        player.printCards();

        String consoleOutput = outputStream.toString();
        assertEquals("[Валет Черви (10), Дама Пики (10), Король Пики (10)] --> 30\n", consoleOutput);

        System.setOut(System.out);
    }

    @Test
    void testPrintCardsD() {
        Deck deck = new Deck(new Card("Черви", "Валет", 10),
                             new Card("Пики", "Дама", 10));

        Player player = new Player();

        player.addCard(deck);
        player.addCard(deck);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        player.printCardsD();

        String consoleOutput = outputStream.toString();
        assertEquals("[Валет Черви (10), <закрытая карта>]\n", consoleOutput);

        System.setOut(System.out);
    }

    @Test
    void testGetTotalScore() {
        Deck deck = new Deck(new Card("Черви", "Туз", 11),
                             new Card("Пики", "Туз", 11));

        Player player = new Player();

        player.addCard(deck);
        player.addCard(deck);

        int total = player.getTotalScore();

        assertEquals(total, 2);
    }

    @Test
    void testGetLast() {
        Deck deck = new Deck(new Card("Черви", "Туз", 11),
                             new Card("Пики", "Туз", 11));

        Player player = new Player();

        player.addCard(deck);
        player.addCard(deck);

        Card card = player.checkLast();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        card.print();

        String consoleOutput = outputStream.toString();
        assertEquals("Туз Пики (11)", consoleOutput);

        System.setOut(System.out);
    }

    @Test
    void testClear() {
        Deck deck = new Deck(new Card("Черви", "Туз", 11),
                             new Card("Пики", "Туз", 11));

        Player player = new Player();

        player.addCard(deck);
        player.addCard(deck);

        player.clear();

        int total = player.getTotalScore();

        assertEquals(total, 0);
    }
}