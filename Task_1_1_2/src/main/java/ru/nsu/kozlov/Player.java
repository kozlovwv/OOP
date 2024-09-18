package ru.nsu.kozlov;

import java.util.LinkedList;

public class Player {
    LinkedList<Card> cards = new LinkedList<Card>();

    void addCard(Card card) {
        cards.add(card);
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

    int getTotalScore () {
        int total = 0;
        for (Card card : cards) {
            total += card.score;
        }
        return total;
    }
}