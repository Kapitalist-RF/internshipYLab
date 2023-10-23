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

    public Player(String name) {
        this.name = name;
        this.account = 0d;
    }

    public Player(int ID, String name, Double account) {
        this.ID = ID;
        this.name = name;
        this.account = account;
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

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Double getAccount() {
        return account;
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
