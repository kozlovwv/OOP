package ru.nsu.kozlov;

/** Main class.
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game(new Card[]{new Card("Пики", "Король", 10),
                                        new Card("Пики", "Тройка", 3),
                                        new Card("Пики", "Туз", 11),
                                        new Card("Пики", "Пятерка", 5),
                                        new Card("Черви", "Пятерка", 5),
                                        new Card("Пики", "Двойка", 2)});
        game.play(1);
    }
}