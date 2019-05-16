/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.trophy.bean.impl;

import com.google.gson.*;
import edu.eci.trophy.bean.Api;
import edu.eci.trophy.model.HttpConnection;
import edu.eci.trophy.model.Player;
import edu.eci.trophy.model.GameMatch;
import edu.eci.trophy.persistance.PlayerRepository;
import edu.eci.trophy.service.TrophyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
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
        this.apiKey = "RGAPI-b0e53c54-bec0-4374-b3fa-7e623aded01e";
    }

    @Override
    public Player getPlayerInfo(String userName) throws TrophyException {
        Player player = null;
        try {
            player = playerRepo.findByName(userName);
        } catch (Exception e) {
            Logger.getLogger(LolApi.class.getName()).log(Level.SEVERE, "Jugador duplicado en DB");
        }
        if (player == null) {
            try {
                String response = HttpConnection.getUrlData("https://la1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + userName + "?api_key=" + apiKey);
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = (JsonObject) jsonParser.parse(response);
                player = new Player(userName, jsonObject.get("accountId").getAsString(), jsonObject.get("id").getAsString());
            } catch (IOException ex) {
                Logger.getLogger(LolApi.class.getName()).log(Level.SEVERE, "Method getAccountId, Param: " + userName);
            }
            playerRepo.save(player);
        }
        return player;
    }

    @Override
    public GameMatch isPlaying(String userName) throws TrophyException {
        Player player = getPlayerInfo(userName);
        GameMatch gameMatch = null;
        try {
            String response = HttpConnection.getUrlData("https://la1.api.riotgames.com/lol/spectator/v4/active-games/by-summoner/" + player.getSummonerId() + "?api_key=" + apiKey);
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(response);
            JsonArray participants = jsonObject.getAsJsonArray("participants");
            Map<String, Boolean> players = new HashMap<>();
            int totalParticipants = participants.size();
            for (int i = 0; i < totalParticipants; i++) {
                JsonParser jsonParser2 = new JsonParser();
                JsonObject participantX = (JsonObject) jsonParser2.parse(participants.get(i).toString());
                players.put(participantX.get("summonerName").getAsString(), false);
            }
            gameMatch = new GameMatch(jsonObject.get("gameId").getAsInt(), jsonObject.get("gameStartTime").getAsLong(), players);
        } catch (IOException e) {
            Logger.getLogger(LolApi.class.getName()).log(Level.SEVERE, "Method isPlaying, Param: " + userName, e);
        }
        return gameMatch;
    }

    @Override
    public Map<Long, Map<String, Boolean>> getResults(Integer gameId) throws TrophyException {
        Map<Long, Map<String, Boolean>> results = new HashMap<>();
        try {
            String response = HttpConnection.getUrlData("https://la1.api.riotgames.com/lol/match/v4/matches/" + gameId + "?api_key=" + apiKey);
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(response);
            JsonArray participantIdentities = jsonObject.getAsJsonArray("participantIdentities");
            JsonArray participants = jsonObject.getAsJsonArray("participants");
            int totalParticipants = participants.size();
            Map<String, Boolean> players = new HashMap<>();
            for (int i = 0; i < totalParticipants; i++) {
                JsonParser jsonParser2 = new JsonParser();
                JsonObject participantX = ((JsonObject) jsonParser2.parse(participantIdentities.get(i).toString())).getAsJsonObject("player");
                JsonObject participantY = ((JsonObject) jsonParser2.parse(participants.get(i).toString())).getAsJsonObject("stats");
                players.put(participantX.get("summonerName").getAsString(), participantY.get("win").getAsBoolean());
            }
            results.put(jsonObject.get("gameDuration").getAsLong() * 1000, players);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

}
