/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.trophy.bean;

import com.google.gson.JsonObject;
import edu.eci.trophy.model.GameMatch;
import edu.eci.trophy.model.Player;
import edu.eci.trophy.service.TrophyException;

import java.util.Map;

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
    Player getPlayerInfo(String userName) throws TrophyException;

    GameMatch isPlaying(String userName) throws TrophyException;

    Map<Long, Map<String, Boolean>> getResults(Integer gameId) throws TrophyException;

}
