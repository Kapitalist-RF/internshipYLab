package consoleProgram;

import player.ActionsPlayer;
import player.HistoryPlayer;
import player.Player;
import player.StatusOperationPlayer;
import transactions.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

/**
 * @author Dmitry
 * This is Class Console Program
 */
public class ConsoleProgram {

    /**
     * This is method start my console program
     *
     * @param reader reads values from the console
     */
    public void startProgram(BufferedReader reader) {
        showMenuChoicePlayers(reader);
    }

    /**
     * This is method show Main menu my console program
     *
     * @param reader reads values from the console
     */
    private void showMenuChoicePlayers(BufferedReader reader) {
        while (true) {
            if (Player.getPlayersList().isEmpty()) {
                System.out.println("Empty list of players! Register a new user?\n1. Yes 2. No");
                int answer = 0;
                try {
                    answer = Integer.parseInt(reader.readLine());
                    if (answer != 1 && answer != 2) {
                        System.out.println("Error! Please enter a number: 1 or 2");
                    } else if (answer == 1) {
                        System.out.println("Please enter a name Player: ");
                        new Player(reader.readLine());
                        System.out.println(StatusOperationPlayer.SUCCESS);
                        System.out.println();
                    } else if (answer == 2) {
                        System.out.println("Exit program");
                        System.exit(0);
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Error! Please enter a number: 1 or 2");
                } catch (IOException e) {
                    System.out.println("Error! Please enter a number: 1 or 2");
                    System.out.println();

                }
            } else {
                System.out.println("Please choice Player or Register a new Player: " +
                        "\n1. Register a new Player\n2. Choice Player\n3. Exit");
                int answer = 0;
                try {
                    answer = Integer.parseInt(reader.readLine());
                    if (answer != 1 && answer != 2 && answer != 3) {
                        System.out.println("Error! Please enter a number: 1 or 2 or 3");
                        System.out.println();
                    } else if (answer == 1) {
                        System.out.println("Please enter a name Player: ");
                        new Player(reader.readLine());
                        System.out.println(StatusOperationPlayer.SUCCESS);
                        System.out.println();
                    } else if (answer == 2) {
                        choicePlayer(reader);
                    } else if (answer == 3) {
                        System.out.println("Exit program");
                        System.exit(0);
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Error! Please enter a number: 1 or 2 or 3");
                } catch (IOException e) {
                    System.out.println("Error! Please enter a number: 1 or 2 or 3");
                }
            }
        }
    }

    /**
     * This is method show choice Player menu my console program
     *
     * @param reader reads values from the console
     */
    private void choicePlayer(BufferedReader reader) {
        System.out.println();
        System.out.println("Please choice Player (enter Number Player): ");
        for (Player player : Player.getPlayersList()) {
            System.out.println((Player.getPlayersList().indexOf(player) + 1) + ") " + player);
        }
        int answer = 0;
        while (true) {
            System.out.println("Enter Number Player:");
            try {
                answer = Integer.parseInt(reader.readLine());
                if (answer < 1 || answer > Player.getPlayersList().size()) {
                    System.out.println("Error! Please enter a number: 1 to " + Player.getPlayersList().size());
                    continue;
                }
            } catch (NumberFormatException | IOException n) {
                System.out.println("Error! Please enter a number: 1 to " + Player.getPlayersList().size());
                continue;
            }
            break;
        }
        menuPlayer(answer, reader);
    }

    /**
     * This is method show Player menu my console program
     *
     * @param indexPlayer index in List {@link Player#playersList} my Player
     * @param reader      reads values from the console
     */
    private void menuPlayer(int indexPlayer, BufferedReader reader) {
        Player player = Player.getPlayersList().get(indexPlayer - 1);
        player.getHistoryPlayer().add(new HistoryPlayer(new Date(), ActionsPlayer.LOGIN, 0d, StatusOperationPlayer.SUCCESS));

        int answer = 0;
        while (true) {
            System.out.println("Our Player: " + player);
            System.out.println("1. Debit Operation\n" +
                    "2. Credit Operation\n" +
                    "3. Show History Player Operation\n" +
                    "4. Show History Player ONLY Transaction Operations\n" +
                    "5. Exit to Main menu");
            System.out.println("Enter Number Action:");
            try {
                answer = Integer.parseInt(reader.readLine());
                if (answer < 1 || answer > 5) {
                    System.out.println("Error! Please enter a number Action: 1 to 5");
                    continue;
                }
            } catch (NumberFormatException | IOException n) {
                System.out.println("Error! Please enter a number Action: 1 to 5");
                continue;
            }
            if (answer == 1) {
                if (Transaction.debitTransaction(player, reader)) {
                    System.out.println(ActionsPlayer.DEBIT.toString() + " " + StatusOperationPlayer.SUCCESS.toString());
                } else {
                    System.out.println(ActionsPlayer.DEBIT.toString() + " " + StatusOperationPlayer.ERROR.toString());
                }
            } else if (answer == 2) {
                if (Transaction.creditTransaction(player, reader)) {
                    System.out.println(ActionsPlayer.CREDIT.toString() + " " + StatusOperationPlayer.SUCCESS.toString());
                } else {
                    System.out.println(ActionsPlayer.CREDIT.toString() + " " + StatusOperationPlayer.ERROR.toString());
                }
            } else if (answer == 3) {
                System.out.println("History Player: ");
                player.getHistoryPlayer().forEach(System.out::println);
            } else if (answer == 4) {
                System.out.println("History Player ONLY Transaction Operations: ");
                player.getHistoryPlayer().stream().filter(o1 -> o1.getActionsPlayer().equals(ActionsPlayer.DEBIT) ||
                        o1.getActionsPlayer().equals(ActionsPlayer.CREDIT)).forEach(System.out::println);
            } else if (answer == 5) {
                System.out.println("Exit to Main Menu");
                player.getHistoryPlayer().add(new HistoryPlayer(new Date(), ActionsPlayer.EXIT, 0d, StatusOperationPlayer.SUCCESS));
                break;
            }
        }
    }
}
