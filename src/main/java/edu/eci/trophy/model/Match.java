package edu.eci.trophy.model;

import java.util.HashMap;

public class Match {

    private String name;
    private String creator;
    private HashMap<String, Integer> bettors;
    private String state;
    private String winner;
    private int id;

    public Match(String name, String creator, HashMap<String, Integer> bettors, String state, String winner, int id) {
        this.name = name;
        this.creator = creator;
        this.bettors = bettors;
        this.state = state;
        this.winner = winner;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public HashMap<String, Integer> getBettors() {
        return bettors;
    }

    public void setBettors(HashMap<String, Integer> bettors) {
        this.bettors = bettors;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
