/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.trophy.bean.impl;

import com.google.gson.JsonObject;
import edu.eci.trophy.bean.Api;
import edu.eci.trophy.model.GameMatch;
import edu.eci.trophy.model.Player;
import edu.eci.trophy.service.TrophyException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 * @author Jonathan Prieto
 */
@Service("Bnb")
public class BnbApi implements Api {

    @Override
    public Player getPlayerInfo(String userName) throws TrophyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GameMatch isPlaying(String userName) throws TrophyException {
        return null;
    }

    @Override
    public Map<Long, Map<String, Boolean>> getResults(Integer gameId) throws TrophyException {
        return null;
    }
}
