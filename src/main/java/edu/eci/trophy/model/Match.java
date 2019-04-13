package edu.eci.trophy.model;

import java.util.HashMap;
import java.util.List;

public class Match {

    private String name;
    private String creator;
    private List<HashMap<String, String>> bettors;
    private String state;
    private String winner;
    private int id;
    private int currentBet;

    public Match(String name, String creator, List<HashMap<String, String>> bettors, String state, String winner, int id, int currentBet) {
        this.name = name;
        this.creator = creator;
        this.bettors = bettors;
        this.state = state;
        this.winner = winner;
        this.id = id;
        this.currentBet = currentBet;
    }

    public Match(){

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

    public List<HashMap<String, String>> getBettors() {
        return bettors;
    }

    public void setBettors(List<HashMap<String, String>> bettors) {
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

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }
}
