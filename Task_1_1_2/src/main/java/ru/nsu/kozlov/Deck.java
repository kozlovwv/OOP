package ru.nsu.kozlov;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/** implementation of deck.
 */
public class Deck {
    final String[] suits = {"Трефы", "Пики", "Черви", "Бубны"};
    final String[] ranks = {"Двойка", "Тройка", "Четверка", "Пятерка", "Шестерка", "Семерка",
                            "Восьммерка", "Девятка", "Десятка", "Валет", "Дама",
                            "Король", "Туз"};

    private final Queue<Card> deck = new LinkedList<>();

    /** create a deck with 52 cards, then shuffle it.
     */
    public Deck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (ranks[j].equals("Валет") || ranks[j].equals("Дама")
                                             || ranks[j].equals("Король")) {
                    deck.offer(new Card(suits[i], ranks[j], 10));
                } else if (ranks[j].equals("Туз")) {
                    deck.offer(new Card(suits[i], ranks[j], 11));
                } else {
                    deck.offer(new Card(suits[i], ranks[j], j + 2));
                }
            }
        }
        Collections.shuffle((LinkedList<Card>) deck);
        System.out.println("Новая колода карт!");
    }

    public Deck(Card card1, Card card2) {
        deck.offer(card1);
        deck.offer(card2);
    }

    /** constructor for PlayerTest.
     *
     * @param card1 first card
     * @param card2 second card
     * @param card3 third card
     */
    public Deck(Card card1, Card card2, Card card3) {
        deck.offer(card1);
        deck.offer(card2);
        deck.offer(card3);
    }

    Card getCard() {
        return deck.poll();
    }

    boolean isEmpty() {
        return deck.isEmpty();
    }

    void remakeDeck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (ranks[j].equals("Валет") || ranks[j].equals("Дама")
                                             || ranks[j].equals("Король")) {
                    deck.offer(new Card(suits[i], ranks[j], 10));
                } else if (ranks[j].equals("Туз")) {
                    deck.offer(new Card(suits[i], ranks[j], 11));
                } else {
                    deck.offer(new Card(suits[i], ranks[j], j + 2));
                }
            }
        }
        Collections.shuffle((LinkedList<Card>) deck);
        System.out.println("Новая колода карт!\n");
    }
}