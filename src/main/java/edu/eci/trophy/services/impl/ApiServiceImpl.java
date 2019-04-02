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
        Player playerCache = searchPlayerDb(name);
        if (playerCache == null) {
            String response = api.getPlayerInfo(name);
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(response);
            Player player = new Player(name, jsonObject.get("accountId").getAsString());
            playerRepo.save(player);
            playerCache = player;
        }
        return playerCache;
    }

    public Player getHistory(Player player) throws TrophyException {
        String response = api.getMatches(player.getAccountId());
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(response);
        player.setTotalGames(jsonObject.get("totalGames").getAsInt());
        JsonArray jsonArray = jsonObject.getAsJsonArray("matches");
        for (int i = 0; i < jsonArray.size() && i == 0; i++) {
//            System.out.println("---" + jsonArray.get(i) + "---");
            JsonParser jsonParser2 = new JsonParser();
            JsonObject jsonObject2 = (JsonObject) jsonParser2.parse(jsonArray.get(i).toString());
//            System.out.println("-*-" + jsonObject2.get("gameId") + "-*-" + jsonObject2.get("timestamp"));
            player.setLastGame(new PlayerMatch(jsonObject2.get("gameId").getAsInt(), jsonObject2.get("timestamp").getAsLong()));
        }
        playerRepo.save(player);
        return player;
    }

    public void getGameDetail(Player player) throws TrophyException {
        String response = api.getMatchDetail(player.getLastGame().getGameId());
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(response);
        player.getLastGame().setGameEnding(jsonObject.get("gameDuration").getAsLong());
    }

    public Player searchPlayerDb(String name) {
        Player playerCache = null;
        try {
            playerCache = playerRepo.findByName(name);
        } catch (Exception e) {
            Logger.getLogger(ApiServiceImpl.class.getName()).log(Level.SEVERE, "Jugador duplicado en DB", e);
        }
        return playerCache;
    }

}
