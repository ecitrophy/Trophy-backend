package edu.eci.trophy.service;

import com.google.gson.JsonObject;
import edu.eci.trophy.model.GameMatch;
import edu.eci.trophy.model.Player;

import java.util.Map;

/**
 * @author Jonathan Prieto
 */
public interface ApiService {

    /**
     * @param name
     * @return
     * @throws edu.eci.trophy.service.TrophyException
     */
    Player getPlayer(String name) throws TrophyException;

    GameMatch isPlaying(String name) throws TrophyException;

    Map<Long, Map<String, Boolean>> getResults(Integer gameId) throws TrophyException;

}
