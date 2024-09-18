package ru.nsu.kozlov;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Deck {
    String[] suits = {"Трефы", "Пики", "Черви", "Бубны"};
    String[] ranks = {"Двойка", "Тройка", "Четверка", "Пятерка", "Шестерка",
                      "Семерка", "Восьмерка", "Девятка", "Десятка", "Валет",
                      "Дама", "Король", "Туз"};

    Queue<Card> deck = new LinkedList<>();

    public Deck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (ranks[j].equals("Валет") || ranks[j].equals("Дама") || ranks[j].equals("Король")) {
                    deck.offer(new Card(suits[i], ranks[j], 10));
                } else if (ranks[j].equals("Туз")) {
                    deck.offer(new Card(suits[i], ranks[j], 11));
                } else {
                    deck.offer(new Card(suits[i], ranks[j], j + 2));
                }
            }
        }
        Collections.shuffle((LinkedList<Card>) deck);
    }

}