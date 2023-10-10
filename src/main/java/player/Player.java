package player;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Dmitry
 * This is Class Player
 */
public class Player {

    /**
     * player database
     */
    private static List<Player> playersList = new ArrayList<>();

    /**
     * unique to players ID
     */
    private static int playerID;

    /**
     * ID player
     */
    private int ID;
    /**
     * Name player
     */
    private String name;

    /**
     * Account player
     */
    private Double account;

    /**
     * History player all actions
     */
    private List<HistoryPlayer> historyPlayer;

    public Player(String name) {
        this.ID = playerID;
        playerID++;
        this.name = name;
        this.account = 0d;
        this.historyPlayer = new ArrayList<>();
        this.historyPlayer.add(new HistoryPlayer(new Date(), ActionsPlayer.REGISTRATION, 0d, StatusOperationPlayer.SUCCESS));
        playersList.add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return ID == player.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    public static List<Player> getPlayersList() {
        return playersList;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Double getAccount() {
        return account;
    }

    public List<HistoryPlayer> getHistoryPlayer() {
        return historyPlayer;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return  "ID=" + ID +
                ", name='" + name + '\'' +
                ", account=" + String.format("%.2f", account);
    }
}
