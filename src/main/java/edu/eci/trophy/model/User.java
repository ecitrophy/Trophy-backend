package edu.eci.trophy.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String userName;
    private int trophyPoints;
    private Map<String, UserBet> bets = new ConcurrentHashMap<String, UserBet>();

    public User() {
    }

    public User(String username, String name, String email, String password) {
        this.userName = username.toLowerCase();
        this.name = name;
        this.email = email.toLowerCase();
        this.password = password;
        this.trophyPoints = 0;
    }

    public User(String username, String name, String email, String password, int trophyPoints) {
        this.userName = username.toLowerCase();
        this.name = name;
        this.email = email.toLowerCase();
        this.password = password;
        this.trophyPoints = trophyPoints;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTrophyPoints() {
        return trophyPoints;
    }

    public void setTrophyPoints(int trophyPoints) {
        this.trophyPoints = trophyPoints;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, UserBet> getBets() {
        return bets;
    }

    public void setBets(Map<String, UserBet> bets) {
        this.bets = bets;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", trophyPoints=" + trophyPoints +
                ", bets=" + bets +
                '}';
    }
}
