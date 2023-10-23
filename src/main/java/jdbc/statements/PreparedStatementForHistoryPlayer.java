package jdbc.statements;


import player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PreparedStatementForHistoryPlayer {

    public static void insertHistoryPlayer(Connection connection, int playerID, Date date, player.ActionsPlayer actionsPlayer,
                                           Double amount, player.StatusOperationPlayer status) throws SQLException {
        String insertDataSQL = "INSERT INTO public.\"users-history-actions\"(id, date, \"actionsPlayer\", account, status)" +
                " VALUES (?, ?, ?, ?, ?)";
        PreparedStatement insertDataStatement = connection.prepareStatement(insertDataSQL);
        insertDataStatement.setInt(1, playerID);
        insertDataStatement.setDate(2, new java.sql.Date(date.getTime()));
        insertDataStatement.setString(3, actionsPlayer.toString());
        insertDataStatement.setDouble(4, amount);
        insertDataStatement.setString(5, status.toString());
        insertDataStatement.executeUpdate();
    }

    public static void retrieveAllHistoryPlayer(Connection connection, Player player) throws SQLException {
        String retrieveDataSQL = "SELECT public.\"users-history-actions\".*\n" +
                "\tFROM public.\"users-liquibase\"\n" +
                "\tINNER JOIN public.\"users-history-actions\" \n" +
                "\tON public.\"users-history-actions\".id = public.\"users-liquibase\".id" +
                "\tWHERE public.\"users-history-actions\".id = ?;";
        PreparedStatement retrieveDataStatement = connection.prepareStatement(retrieveDataSQL);
        retrieveDataStatement.setInt(1, player.getID());
        ResultSet resultSet = retrieveDataStatement.executeQuery();
        while (resultSet.next()) {
            Date date = resultSet.getDate("date");
            String actionsPlayer = resultSet.getString("actionsPlayer");
            double account = resultSet.getDouble("account");
            String status = resultSet.getString("status");
            System.out.println("date: " + date + ", Actions Player: " + actionsPlayer + ", Account: " + account +
                    ", Status: " + status);
        }
    }

    public static void retrieveTransactionsHistoryPlayer(Connection connection, Player player) throws SQLException {
        String retrieveDataSQL = "SELECT public.\"users-history-actions\".*\n" +
                "\tFROM public.\"users-liquibase\"\n" +
                "\tINNER JOIN public.\"users-history-actions\" \n" +
                "\tON public.\"users-history-actions\".id = public.\"users-liquibase\".id \n" +
                "\tWHERE \"actionsPlayer\" IN ('CREDIT', 'DEBIT') AND public.\"users-history-actions\".id = ?;";
        PreparedStatement retrieveDataStatement = connection.prepareStatement(retrieveDataSQL);
        retrieveDataStatement.setInt(1, player.getID());
        ResultSet resultSet = retrieveDataStatement.executeQuery();
        while (resultSet.next()) {
            Date date = resultSet.getDate("date");
            String actionsPlayer = resultSet.getString("actionsPlayer");
            double account = resultSet.getDouble("account");
            String status = resultSet.getString("status");
            System.out.println("date: " + date + ", Actions Player: " + actionsPlayer + ", Account: " + account +
                    ", Status: " + status);
        }
    }

}
