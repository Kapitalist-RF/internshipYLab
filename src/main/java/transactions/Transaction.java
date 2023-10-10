package transactions;

import error.ErrorID;
import player.ActionsPlayer;
import player.HistoryPlayer;
import player.Player;
import player.StatusOperationPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dmitry
 * This Class for cash transactions over balance
 */
public class Transaction {

    /**
     * DataBase for transaction ID
     */
    private static List<Integer> transactionID = new ArrayList<>();

    /**
     * Debit operation
     * @param player Player
     * @param reader reads values from the console
     */
    public static boolean debitTransaction(Player player, BufferedReader reader) {

        System.out.print("Enter the TransactionID: ");
        int transactionDebitID = 0;
        try {
            transactionDebitID = Integer.parseInt(reader.readLine());
            if (transactionID.contains(transactionDebitID)) {
                try {
                    throw new ErrorID("Error TransactionID");
                } catch (ErrorID errorID) {
                    System.out.println(errorID.getMessage());
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter the number! You entered " + e.getMessage().split(":")[1].trim());
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print("Enter the debit amount: ");
        double accountDebit = 0;
        try {
            accountDebit = Double.parseDouble(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Enter the number! You entered " + e.getMessage().split(":")[1].trim());
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        double resultAccount = player.getAccount() - accountDebit;
        if (resultAccount >= 0) {
            player.setAccount(resultAccount);
            transactionID.add(transactionDebitID);
            player.getHistoryPlayer().add(new HistoryPlayer(new Date(), ActionsPlayer.DEBIT, accountDebit, StatusOperationPlayer.SUCCESS));
            return true;
        }
        player.getHistoryPlayer().add(new HistoryPlayer(new Date(), ActionsPlayer.DEBIT, accountDebit, StatusOperationPlayer.ERROR));

        return false;
    }

    /**
     * Credit operation
     * @param player Player
     * @param reader reads values from the console
     */
    public static boolean creditTransaction(Player player, BufferedReader reader) {

        System.out.print("Enter the TransactionID: ");
        int transactionCreditID = 0;
        try {
            transactionCreditID = Integer.parseInt(reader.readLine());
            if (transactionID.contains(transactionCreditID)) {
                try {
                    throw new ErrorID("Error TransactionID");
                } catch (ErrorID errorID) {
                    System.out.println(errorID.getMessage());
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter the number! You entered " + e.getMessage().split(":")[1].trim());
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print("Enter the credit amount: ");
        double accountCredit = 0;
        try {
            accountCredit = Double.parseDouble(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Enter the number! You entered " + e.getMessage().split(":")[1].trim());
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        double resultAccount = player.getAccount() + accountCredit;
        player.setAccount(resultAccount);
        transactionID.add(transactionCreditID);
        player.getHistoryPlayer().add(new HistoryPlayer(new Date(), ActionsPlayer.CREDIT, accountCredit, StatusOperationPlayer.SUCCESS));
        return true;

    }

    public static List<Integer> getTransactionID() {
        return transactionID;
    }
}
