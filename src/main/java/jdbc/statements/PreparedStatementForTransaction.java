package jdbc.statements;

import player.ActionsPlayer;
import player.Player;
import player.StatusOperationPlayer;

import java.sql.*;
import java.util.Date;

public class PreparedStatementForTransaction {



    public static boolean likeTransaction (Connection connection, int transactionID) throws SQLException {
        String retrieveIDSQL = "SELECT * FROM public.\"transaction-id\" WHERE transaction_id = ? LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(retrieveIDSQL);
        statement.setInt(1, transactionID);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int resultInt = resultSet.getInt(1);
            return resultInt == transactionID;
        }
        return false;
    }

    public static void insertTransaction(Connection connection, int transactionID) throws SQLException {
        String insertDataSQL = "INSERT INTO public.\"transaction-id\"(transaction_id) VALUES (?)";
        java.sql.PreparedStatement insertDataStatement = connection.prepareStatement(insertDataSQL);
        insertDataStatement.setInt(1, transactionID);
        insertDataStatement.executeUpdate();
    }

}
