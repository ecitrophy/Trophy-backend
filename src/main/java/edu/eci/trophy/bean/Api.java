/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.trophy.bean;

import edu.eci.trophy.model.Player;
import edu.eci.trophy.model.TrophyException;

/**
 *
 * @author Jonathan Prieto
 */
public interface Api {

    /**
     * Obtener la informacion de un usuario por medio de su nick o nombre de
     * usuario en la plataforma
     *
     * @param userName
     * @return
     * @throws TrophyException
     */
    public Player getPlayerInfo(String userName) throws TrophyException;

    /**
     *
     * @param accountId
     * @return
     * @throws TrophyException
     */
    public String getMatches(String accountId) throws TrophyException;

    /**
     *
     * @param gameId
     * @return
     * @throws TrophyException
     */
    public String getMatchDetail(Integer gameId) throws TrophyException;

}
