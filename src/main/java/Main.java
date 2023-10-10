import consoleProgram.ConsoleProgram;
import player.Player;
import transactions.Transaction;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            ConsoleProgram consoleProgram = new ConsoleProgram();
            consoleProgram.startProgram(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
