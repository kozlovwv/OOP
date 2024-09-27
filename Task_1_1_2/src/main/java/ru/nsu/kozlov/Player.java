package ru.nsu.kozlov;

import java.util.LinkedList;

public class Player {
    private final LinkedList<Card> cards = new LinkedList<Card>();

    void addCard(Deck deck) {
        if (deck.isEmpty()) {
            deck.remakeDeck();
        }
        cards.add(deck.getCard());
    }

    void printCards() {
        int totalScore = getTotalScore();
        int totalCards = cards.size();
        Card card;

        System.out.print("[");
        for (int i = 0; i < totalCards - 1; i++) {
            card = cards.get(i);
            card.print();
            System.out.print(", ");
        }
        card = cards.get(totalCards - 1);
        card.print();

        System.out.print("] --> " + totalScore + "\n");
    }

    void printCardsD() {
        System.out.print("[");
        cards.getFirst().print();
        System.out.print(", <закрытая карта>]\n");
    }

    int getTotalScore() {
        int total = 0;

        for (Card card : cards) {
            total += card.score;
        }

        if (total > 21) {
            for (Card card : cards) {
                if (card.rank.equals("Туз") && (card.score == 11)) {
                    card.score -= 10;
                    total -= 10;
                }
            }
        }

        return total;
    }

    Card checkLast() {
        return cards.getLast();
    }

    void clear() {
        cards.clear();
    }
}