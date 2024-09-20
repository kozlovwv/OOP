package ru.nsu.kozlov;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void testIsEmpty() {
        Deck deck = new Deck();
        boolean isEmpty = deck.isEmpty();
        assertFalse(isEmpty);
    }

    @Test
    void testIsEmpty2() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.getCard();
        }
        boolean isEmpty = deck.isEmpty();
        assertTrue(isEmpty);
    }
}