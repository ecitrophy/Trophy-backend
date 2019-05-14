package edu.eci.trophy.model;

import java.util.ArrayList;
import java.util.List;

public class Match {

    private String id;
    private String name;
    private Game game;
    private Integer minimumBet;
    private User creator;
    private MatchStatus state;
    private List<User> bettors = new ArrayList<>();
    private List<User> winner = new ArrayList<>();

    public Match() {
    }

    public Match(String name, Game game, Integer minimumBet, User creator, MatchStatus state) {
        this.name = name;
        this.game = game;
        this.minimumBet = minimumBet;
        this.creator = creator;
        this.bettors.add(creator);
        this.state = state;
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getMinimumBet() {
        return minimumBet;
    }

    public void setMinimumBet(Integer minimumBet) {
        this.minimumBet = minimumBet;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public MatchStatus getState() {
        return state;
    }

    public void setState(MatchStatus state) {
        this.state = state;
    }

    public List<User> getBettors() {
        return bettors;
    }

    public void setBettors(List<User> bettors) {
        this.bettors = bettors;
    }

    public List<User> getWinner() {
        return winner;
    }

    public void setWinner(List<User> winner) {
        this.winner = winner;
    }
}
