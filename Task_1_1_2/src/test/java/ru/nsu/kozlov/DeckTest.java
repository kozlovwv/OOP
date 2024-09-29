package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** tests for class Deck.
 */
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

    @Test
    void testGetCard() {
        Deck deck = new Deck();
        Card card = deck.getCard();
        assertNotNull(card);
    }

    @Test
    void testRemakeDeck() {
        Deck deck = new Deck();
        Deck deck2 = new Deck();

        for (int i = 0; i < 52; i++) {
            deck.getCard();
            deck2.getCard();
        }

        deck.remakeDeck();
        deck2.remakeDeck();

        assertNotEquals(deck, deck2);
    }
}