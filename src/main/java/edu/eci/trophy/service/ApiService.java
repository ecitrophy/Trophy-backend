/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.trophy.service;

import edu.eci.trophy.model.Player;
import edu.eci.trophy.model.TrophyException;

/**
 *
 * @author Jonathan Prieto
 */
public interface ApiService {

    /**
     *
     * @param name
     * @return
     * @throws edu.eci.trophy.model.TrophyException
     */
    public Player getPlayer(String name) throws TrophyException;

}
