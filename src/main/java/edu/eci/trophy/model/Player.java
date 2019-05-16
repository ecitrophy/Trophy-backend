package edu.eci.trophy.model;

/**
 *
 * @author Jonathan Prieto
 */
public class Player {

    public String id;
    private String name;
    private String accountId;
    private String summonerId;

    public Player() {
    }

    public Player(String name, String accountId, String summonerId) {
        this.name = name;
        this.accountId = accountId;
        this.summonerId = summonerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", accountId='" + accountId + '\'' +
                ", summonerId='" + summonerId + '\'' +
                '}';
    }
}
