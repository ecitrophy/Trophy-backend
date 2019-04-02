/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.trophy.model;

/**
 *
 * @author Jonathan Prieto
 */
public class Player {

    public String id;

    private String name;
    private String accountId;
    private Integer totalGames;
    private PlayerMatch lastGame;

    public Player() {
    }

    public Player(String name, String accountId) {
        this.name = name;
        this.accountId = accountId;
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

    public Integer getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(Integer totalGames) {
        this.totalGames = totalGames;
    }

    public PlayerMatch getLastGame() {
        return lastGame;
    }

    public void setLastGame(PlayerMatch lastGame) {
        this.lastGame = lastGame;
    }

    @Override
    public String toString() {
        return String.format("Player[id=%s, name='%s', accountId='%s', totalGames='%d', lastGame=" + lastGame.toString() + "]", id, name, accountId, totalGames);
    }

}
