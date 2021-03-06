package edu.eci.trophy.services.impl;

import com.google.gson.JsonObject;
import edu.eci.trophy.bean.Api;
import edu.eci.trophy.model.GameMatch;
import edu.eci.trophy.model.Player;
import edu.eci.trophy.service.TrophyException;
import edu.eci.trophy.persistance.PlayerRepository;
import edu.eci.trophy.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 * @author Jonathan Prieto
 */
@Service("Apiservice")
public class ApiServiceImpl implements ApiService {

    @Autowired
    @Qualifier("Lol")
    Api api;

    @Override
    public Player getPlayer(String name) throws TrophyException {
        return api.getPlayerInfo(name);
    }

    @Override
    public GameMatch isPlaying(String name) throws TrophyException {
        return api.isPlaying(name);
    }

    @Override
    public Map<Long, Map<String, Boolean>> getResults(Integer gameId) throws TrophyException {
        return api.getResults(gameId);
    }
}
