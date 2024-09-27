package ru.nsu.kozlov;

/** implementation of card methods.
 */
public class Card {
    String suit;
    String rank;
    int score;

    /** Card constructor.
     *
     * @param suit of card
     * @param rank of card
     * @param score of card
     */
    public Card(String suit, String rank, int score) {
        this.suit = suit;
        this.rank = rank;
        this.score = score;
    }

    /** printing card's rank, suit and score.
     */
    void print() {
        System.out.print(rank + " " + suit + " (" + score + ")");
    }
}