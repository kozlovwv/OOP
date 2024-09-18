package ru.nsu.kozlov;

public class Card {
    String suit;
    String rank;
    int score;

    public Card(String suit, String rank, int score) {
        this.suit = suit;
        this.rank = rank;
        this.score = score;
    }

    void print() {
        System.out.print(this.rank + " " + this.suit + " (" + this.score + ")");
    }
}