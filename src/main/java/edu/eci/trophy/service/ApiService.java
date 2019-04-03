package edu.eci.trophy.service;

import edu.eci.trophy.model.Player;

/**
 *
 * @author Jonathan Prieto
 */
public interface ApiService {

    /**
     *
     * @param name
     * @return
     * @throws edu.eci.trophy.service.TrophyException
     */
    public Player getPlayer(String name) throws TrophyException;

}
