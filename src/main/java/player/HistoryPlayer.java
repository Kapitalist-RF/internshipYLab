package player;

import java.util.Date;

/**
 * @author Dmitry
 * This is Class for player history
 */
public class HistoryPlayer {

    /**
     * Date action Player
     */
    private Date date;

    /**
     * Actions Player
     */
    private ActionsPlayer actionsPlayer;

    /**
     * Actions amount
     */
    private Double amount;

    /**
     * Status Operation Player
     */
    private StatusOperationPlayer status;

    public HistoryPlayer(Date date, ActionsPlayer actionsPlayer, Double amount, StatusOperationPlayer statusOperationPlayer) {
        this.date = date;
        this.actionsPlayer = actionsPlayer;
        this.amount = amount;
        this.status = statusOperationPlayer;
    }

    public Date getDate() {
        return date;
    }

    public ActionsPlayer getActionsPlayer() {
        return actionsPlayer;
    }

    public Double getAmount() {
        return amount;
    }

    public StatusOperationPlayer getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return  "date=" + date +
                ", actionsPlayer=" + actionsPlayer +
                ", amount=" + String.format("%.2f", amount) +
                ", status=" + status +
                '}';
    }
}
