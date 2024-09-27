package ru.nsu.kozlov;

import java.util.Scanner;

/** implementation of BlackJack game.
 */
public class Game {
    static void play(int rounds) {
        int playerScore = 0;
        int dealerScore = 0;
        int roundsCounter = 0;
        boolean isOver;
        String playerMove;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в Блэкджек!");

        Deck deck = new Deck();
        Player player = new Player();
        Player dealer = new Player();

        while (roundsCounter < rounds) {
            isOver = false;
            roundsCounter++;

            player.clear();
            dealer.clear();

            player.addCard(deck);
            player.addCard(deck);
            dealer.addCard(deck);
            dealer.addCard(deck);

            System.out.println("\nРаунд " + roundsCounter);

            System.out.print("\tВаши карты: ");
            player.printCards();
            System.out.print("\tКарты диллера: ");
            dealer.printCardsD();

            if (player.getTotalScore() == 21) {
                System.out.println("\nХод дилера\n-------");
                System.out.print("Дилер открывает закрытую карту ");
                dealer.checkLast().print();
                System.out.println();

                System.out.print("\tВаши карты: ");
                player.printCards();
                System.out.print("\tКарты диллера: ");
                dealer.printCards();

                if (player.getTotalScore() > dealer.getTotalScore()) {
                    System.out.println("Вы выиграли раунд!");
                    playerScore++;
                    printScores(playerScore, dealerScore);
                } else {
                    System.out.println("Ничья!");
                    printScores(playerScore, dealerScore);
                }
            } else {
                System.out.println("\nВаш ход\n-------\nВведите “1”, "
                                   + "чтобы взять карту, и “0”, чтобы остановиться .");

                playerMove = scanner.next();
                while (!(playerMove.equals("0") || playerMove.equals("1"))) {
                    System.out.println("Введите корректное значение!");
                    playerMove = scanner.next();
                }

                while (playerMove.equals("1")) {
                    player.addCard(deck);
                    int total = player.getTotalScore();

                    System.out.print("Вы открыли карту ");
                    player.checkLast().print();
                    System.out.println();

                    System.out.print("\tВаши карты: ");
                    player.printCards();
                    System.out.print("\tКарты диллера: ");
                    dealer.printCardsD();

                    if (total > 21) {
                        System.out.println("Вы проиграли раунд!");
                        dealerScore++;
                        printScores(playerScore, dealerScore);
                        isOver = true;
                        break;
                    } else if (total == 21) {
                        break;
                    }
                    System.out.println("\nВведите “1”, чтобы взять карту, "
                                       + "и “0”, чтобы остановиться .");

                    playerMove = scanner.next();
                    while (!(playerMove.equals("0") || playerMove.equals("1"))) {
                        System.out.println("Введите корректное значение!");
                        playerMove = scanner.next();
                    }
                }

                if (!isOver) {
                    System.out.println("\nХод дилера\n-------");
                    System.out.print("Дилер открывает закрытую карту ");
                    dealer.checkLast().print();
                    System.out.println();

                    System.out.print("\tВаши карты: ");
                    player.printCards();
                    System.out.print("\tКарты диллера: ");
                    dealer.printCards();

                    if (dealer.getTotalScore() == 21) {
                        dealerScore++;
                        System.out.println("Вы проиграли раунд!");
                        printScores(playerScore, dealerScore);
                        isOver = true;
                    }

                    while (dealer.getTotalScore() < 17) {
                        dealer.addCard(deck);
                        int total = dealer.getTotalScore();

                        System.out.println();
                        System.out.print("Дилер открывает карту ");
                        dealer.checkLast().print();
                        System.out.println();

                        System.out.print("\tВаши карты: ");
                        player.printCards();
                        System.out.print("\tКарты диллера: ");
                        dealer.printCards();

                        if (total > 21) {
                            System.out.println("Вы выиграли раунд!");
                            playerScore++;
                            printScores(playerScore, dealerScore);
                            isOver = true;
                            break;
                        } else if (total == 21) {
                            break;
                        }
                    }
                }

                if (!isOver) {
                    if (player.getTotalScore() > dealer.getTotalScore()) {
                        System.out.println("Вы выиграли раунд!");
                        playerScore++;
                        printScores(playerScore, dealerScore);
                    } else if (player.getTotalScore() < dealer.getTotalScore()) {
                        dealerScore++;
                        System.out.println("Вы проиграли раунд!");
                        printScores(playerScore, dealerScore);
                    } else {
                        System.out.println("Ничья!");
                        printScores(playerScore, dealerScore);
                    }
                }
            }
        }
    }

    static void printScores(int plS, int dlS) {
        if (plS > dlS) {
            System.out.println("Счет " + plS + ":" + dlS + " в вашу пользу");
        } else if (plS == dlS) {
            System.out.println("Счет " + plS + ":" + dlS);
        } else {
            System.out.println("Счет " + plS + ":" + dlS + " в пользу дилера");
        }
    }
}
