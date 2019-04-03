package edu.eci.trophy.model;

/**
 *
 * @author Jonathan Prieto
 */
public class PlayerMatch {

    private Integer gameId;
    private long gameCreation;
    private long gameEnding;
    private boolean win;

    public PlayerMatch() {
    }

    public PlayerMatch(Integer gameId, long gameCreation) {
        this.gameId = gameId;
        this.gameCreation = gameCreation;
    }

    public PlayerMatch(Integer gameId, long gameCreation, long gameEnding, boolean win) {
        this.gameId = gameId;
        this.gameCreation = gameCreation;
        this.gameEnding = gameEnding;
        this.win = win;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public long getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(long gameCreation) {
        this.gameCreation = gameCreation;
    }

    public long getGameEnding() {
        return gameEnding;
    }

    public void setGameEnding(long gameEnding) {
        this.gameEnding = gameEnding;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    @Override
    public String toString() {
        return "PlayerMatch{" + "gameId=" + gameId + ", gameCreation=" + gameCreation + ", gameEnding=" + gameEnding + ", win=" + win + '}';
    }

}
