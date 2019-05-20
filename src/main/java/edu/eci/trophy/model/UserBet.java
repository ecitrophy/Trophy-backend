package edu.eci.trophy.model;

public class UserBet {

    private String id;
    private String player;
    private Integer bet;
    private boolean state;
    private Integer amount = 0;

    public UserBet() {
    }

    public UserBet(String id, String player, Integer bet) {
        this.id = id;
        this.player = player;
        this.bet = bet;
    }
    public UserBet( String player, Integer bet) {
        this.player = player;
        this.bet = bet;
        this.state=false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Integer getBet() {
        return bet;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "UserBet{" +
                "id=" + id +
                ", player='" + player + '\'' +
                ", bet=" + bet +
                ", state=" + state +
                ", amount=" + amount +
                '}';
    }
}
