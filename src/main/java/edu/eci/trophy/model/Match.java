package edu.eci.trophy.model;

import java.util.ArrayList;
import java.util.List;

public class Match {

    private String id;
    private String name;
    private Game game;
    private Integer minimumBet;
    private Integer pot = 0;
    private User creator;
    private MatchStatus state;
    private List<User> bettors = new ArrayList<>();
    private List<User> winner = new ArrayList<>();
    private GameMatch gameMatch;

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

    public Integer getPot() {
        return pot;
    }

    public void setPot(Integer pot) {
        this.pot = pot;
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

    public GameMatch getGameMatch() {
        return gameMatch;
    }

    public void setGameMatch(GameMatch gameMatch) {
        this.gameMatch = gameMatch;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", game=" + game +
                ", minimumBet=" + minimumBet +
                ", pot=" + pot +
                ", creator=" + creator +
                ", state=" + state +
                ", bettors=" + bettors +
                ", winner=" + winner +
                ", gameMatch=" + gameMatch +
                '}';
    }
}
