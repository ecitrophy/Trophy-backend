package edu.eci.trophy.trophybackend.models;

import java.util.HashMap;
import java.util.List;

public class Match {

    private User creator;
    private HashMap<User, Integer> bettors;
    private String state;
    private User winner;

    public Match(User creator, HashMap<User, Integer> bettors, String state, User winner) {
        this.creator = creator;
        this.bettors = bettors;
        this.state = state;
        this.winner = winner;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public HashMap<User, Integer> getBettors() {
        return bettors;
    }

    public void setBettors(HashMap<User, Integer> bettors) {
        this.bettors = bettors;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }
}
