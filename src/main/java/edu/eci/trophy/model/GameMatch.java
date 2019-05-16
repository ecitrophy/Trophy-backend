package edu.eci.trophy.model;

import java.util.List;
import java.util.Map;

/**
 * @author Jonathan Prieto
 */
public class GameMatch {

    private Integer gameId;
    private long gameStartTime;
    private long gameEnding;
    private Map<String, Boolean> players;

    public GameMatch() {
    }

    public GameMatch(Integer gameId, long gameStartTime, Map<String, Boolean> players) {
        this.gameId = gameId;
        this.gameStartTime = gameStartTime;
        this.players = players;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public long getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(long gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    public long getGameEnding() {
        return gameEnding;
    }

    public void setGameEnding(long gameEnding) {
        this.gameEnding = gameEnding;
    }

    public Map<String, Boolean> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, Boolean> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "GameMatch{" +
                "gameId=" + gameId +
                ", gameStartTime=" + gameStartTime +
                ", gameEnding=" + gameEnding +
                ", players=" + players +
                '}';
    }
}
