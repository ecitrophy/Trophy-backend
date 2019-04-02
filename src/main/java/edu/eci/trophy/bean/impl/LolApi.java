/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.trophy.bean.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.eci.trophy.bean.Api;
import edu.eci.trophy.model.HttpConnection;
import edu.eci.trophy.model.Player;
import edu.eci.trophy.model.PlayerMatch;
import edu.eci.trophy.model.TrophyException;
import edu.eci.trophy.persistance.PlayerRepository;
import edu.eci.trophy.services.impl.ApiServiceImpl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonathan Prieto
 */
@Service("Lol")
public class LolApi implements Api {

    private final int season;
    private final String apiKey;

    @Autowired
    PlayerRepository playerRepo;

    public LolApi() {
        this.season = 13;
        this.apiKey = "RGAPI-1e7ceffd-0339-4c44-9e4f-221b54e33503";
    }

    @Override
    public Player getPlayerInfo(String userName) throws TrophyException {
        Player player = null;
        try {
            player = playerRepo.findByName(userName);
        } catch (Exception e) {
            Logger.getLogger(ApiServiceImpl.class.getName()).log(Level.SEVERE, "Jugador duplicado en DB", e);
        }
        if (player == null) {
            try {
                String response = HttpConnection.getUrlData("https://la1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + userName + "?api_key=" + apiKey);
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = (JsonObject) jsonParser.parse(response);
                player = new Player(userName, jsonObject.get("accountId").getAsString());

            } catch (IOException ex) {
                Logger.getLogger(LolApi.class.getName()).log(Level.SEVERE, "Method getAccountId, Param: " + userName, ex);
            }
        }
        try {
            player = getLastMatch(player);
        } catch (IOException ex) {
            Logger.getLogger(LolApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        playerRepo.save(player);
        return player;
    }

    public Player getLastMatch(Player player) throws TrophyException, IOException {
        String response = HttpConnection.getUrlData("https://la1.api.riotgames.com/lol/match/v4/matchlists/by-account/" + player.getAccountId() + "?season=" + season + "&api_key=" + apiKey);
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
        return player;
    }

    public String getMatchDetail(Integer gameId) throws TrophyException {
        String response = null;
        try {
            response = HttpConnection.getUrlData("https://la1.api.riotgames.com/lol/match/v4/matches/" + gameId + "?api_key=" + apiKey);
        } catch (IOException ex) {
            Logger.getLogger(LolApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

}
