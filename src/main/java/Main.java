import consoleProgram.ConsoleProgram;
import jdbc.liquibase.LiquibaseForMyBase;
import org.postgresql.util.PSQLException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException {
        while (true) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                 Connection connection = jdbc.conection.Connection.connectionSQL()) {
                ConsoleProgram consoleProgram = new ConsoleProgram();
                consoleProgram.startProgram(reader, connection);
            } catch (PSQLException e) {
                try (Connection connection = jdbc.conection.Connection.connectionSQL()){
                    LiquibaseForMyBase.liquibaseUpdate(connection);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                continue;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            break;
        }
    }

}
