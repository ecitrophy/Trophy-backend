package edu.eci.trophy.services.impl;

import edu.eci.trophy.bean.Api;
import edu.eci.trophy.model.Player;
import edu.eci.trophy.service.TrophyException;
import edu.eci.trophy.persistance.PlayerRepository;
import edu.eci.trophy.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonathan Prieto
 */
@Service("Apiservice")
public class ApiServiceImpl implements ApiService {

    @Autowired
    @Qualifier("Lol")
    Api api;

    @Autowired
    PlayerRepository playerRepo;

    @Override
    public Player getPlayer(String name) throws TrophyException {
        return api.getPlayerInfo(name);
    }

}
