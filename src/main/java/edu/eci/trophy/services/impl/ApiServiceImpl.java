/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.trophy.services.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.eci.trophy.bean.Api;
import edu.eci.trophy.model.Player;
import edu.eci.trophy.model.PlayerMatch;
import edu.eci.trophy.model.TrophyException;
import edu.eci.trophy.persistance.PlayerRepository;
import edu.eci.trophy.service.ApiService;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

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
