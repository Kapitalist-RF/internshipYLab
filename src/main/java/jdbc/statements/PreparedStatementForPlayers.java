package jdbc.statements;

import player.ActionsPlayer;
import player.Player;
import player.StatusOperationPlayer;

import java.sql.*;
import java.util.Date;

public class PreparedStatementForPlayers {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {

            PreparedStatementForTransaction.insertTransaction(connection, 1);
            PreparedStatementForTransaction.likeTransaction(connection, 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printPlayers(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("username");
            double account = resultSet.getDouble("account");
            System.out.println("ID: " + id + ", Name: " + name + ", Account: " + account);
        }
    }

    public static ResultSet retrievePlayers(Connection connection) throws SQLException {
        String retrieveDataSQL = "SELECT * FROM public.\"users-liquibase\" ORDER BY id";
        Statement retrieveDataStatement = connection.createStatement();
        ResultSet resultSet = retrieveDataStatement.executeQuery(retrieveDataSQL);
        return resultSet;
    }

    public static void insertPlayer(Connection connection, Player player) throws SQLException {
        String insertDataSQL = "INSERT INTO public.\"users-liquibase\"(username, account) VALUES (?, ?)";
        java.sql.PreparedStatement insertDataStatement = connection.prepareStatement(insertDataSQL);
        insertDataStatement.setString(1, player.getName());
        insertDataStatement.setDouble(2, 0.0);
        insertDataStatement.executeUpdate();

        PreparedStatementForHistoryPlayer.insertHistoryPlayer(connection, lastPlayerID(connection), new Date(),
                ActionsPlayer.REGISTRATION, 0.0, StatusOperationPlayer.SUCCESS);
    }

    public static void updatePlayerWithHistory(Connection connection, Player player, double accountTransaction, ActionsPlayer actionsPlayer) throws SQLException {

        String updateDataSQL = "UPDATE public.\"users-liquibase\" SET account = ? WHERE id = ?";
        java.sql.PreparedStatement updateDataStatement = connection.prepareStatement(updateDataSQL);
        updateDataStatement.setDouble(1, player.getAccount());
        updateDataStatement.setInt(2, player.getID());
        updateDataStatement.executeUpdate();

        PreparedStatementForHistoryPlayer.insertHistoryPlayer(connection,player.getID(), new Date(), actionsPlayer,
                accountTransaction, StatusOperationPlayer.SUCCESS);
    }

    public static ResultSet retrievePlayer(Connection connection, int playerID) throws SQLException {
        String retrieveDataSQL = "SELECT * FROM public.\"users-liquibase\" WHERE id = ?";
        PreparedStatement retrieveDataStatement = connection.prepareStatement(retrieveDataSQL);
        retrieveDataStatement.setInt(1, playerID);
        ResultSet resultSet = retrieveDataStatement.executeQuery();
        return resultSet;
    }

    private static int lastPlayerID(Connection connection) throws SQLException {
        String retrieveIDSQL = "SELECT id FROM public.\"users-liquibase\" ORDER BY id DESC LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(retrieveIDSQL);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }

    public static int countPlayers(Connection connection) throws SQLException {
        String retrieveIDSQL = "SELECT count(*) FROM public.\"users-liquibase\"";
        PreparedStatement statement = connection.prepareStatement(retrieveIDSQL);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt("count");
    }

    public static Player returnPlayerSQL(Connection connection, int playerID) throws SQLException {
        Player player = null;
        ResultSet resultSet = retrievePlayer(connection, playerID);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("username");
            double account = resultSet.getDouble("account");
            player = new Player(id, name, account);
            PreparedStatementForHistoryPlayer.insertHistoryPlayer(connection, id, new Date(),
                    ActionsPlayer.LOGIN, 0.0, StatusOperationPlayer.SUCCESS);
        }
        return player;
    }



}
