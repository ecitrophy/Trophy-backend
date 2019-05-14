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
import edu.eci.trophy.persistance.PlayerRepository;
import edu.eci.trophy.service.TrophyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        this.apiKey = "RGAPI-1030a826-82db-43c0-bc70-c38e07c36e7c";
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
        }
        try {
            player = getLastMatch(player);
            if (!player.getLastGame().getGameId().equals(-1)) {
                player = getMatchDetail(player);
            }
        } catch (IOException ex) {
            Logger.getLogger(LolApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        playerRepo.save(player);
        return player;
    }

    /**
     *
     * @param player
     * @return
     * @throws TrophyException
     * @throws IOException
     */
    private Player getLastMatch(Player player) throws TrophyException, IOException {
        String response = HttpConnection.getUrlData("https://la1.api.riotgames.com/lol/match/v4/matchlists/by-account/" + player.getAccountId() + "?season=" + season + "&api_key=" + apiKey);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(response);
        player.setTotalGames(jsonObject.get("totalGames").getAsInt());
        JsonArray matches = jsonObject.getAsJsonArray("matches");
        int matchesSize = matches.size();
        if (matchesSize > 0) {
            for (int i = 0; i < matchesSize && i == 0; i++) {
//            System.out.println("---" + jsonArray.get(i) + "---");
                JsonParser jsonParser2 = new JsonParser();
                JsonObject jsonObject2 = (JsonObject) jsonParser2.parse(matches.get(i).toString());
//            System.out.println("-*-" + jsonObject2.get("gameId") + "-*-" + jsonObject2.get("timestamp"));
                player.setLastGame(new PlayerMatch(jsonObject2.get("gameId").getAsInt(), jsonObject2.get("timestamp").getAsLong()));
            }
        } else {
            player.setLastGame(new PlayerMatch(-1, -1, -1, false));
        }

        return player;
    }

    /**
     *
     * @param player
     * @return
     * @throws TrophyException
     * @throws IOException
     */
    private Player getMatchDetail(Player player) throws TrophyException, IOException {
        String response = HttpConnection.getUrlData("https://la1.api.riotgames.com/lol/match/v4/matches/" + player.getLastGame().getGameId() + "?api_key=" + apiKey);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(response);
        long gameDuration = jsonObject.get("gameDuration").getAsLong() * 1000 + player.getLastGame().getGameCreation();
        player.getLastGame().setGameEnding(gameDuration);
        JsonArray participantIdentities = jsonObject.getAsJsonArray("participantIdentities");
        boolean founded = false;
        int totalParticipants = participantIdentities.size();
        for (int i = 0; i < totalParticipants && !founded; i++) {
            JsonParser jsonParser2 = new JsonParser();
            JsonObject participantX = (JsonObject) jsonParser2.parse(participantIdentities.get(i).toString());
            if (participantX.get("player").getAsJsonObject().get("accountId").getAsString().equals(player.getAccountId())) {
                founded = true;
                int participantId = participantX.get("participantId").getAsInt();
                JsonArray teams = jsonObject.getAsJsonArray("teams");
                JsonParser jsonParser3 = new JsonParser();
                if (participantId > (totalParticipants / 2)) {
                    JsonObject team = (JsonObject) jsonParser3.parse(teams.get(1).toString());
                    player.getLastGame().setWin(team.get("win").getAsString().equals("Win"));
                } else {
                    JsonObject team = (JsonObject) jsonParser3.parse(teams.get(0).toString());
                    player.getLastGame().setWin(team.get("win").getAsString().equals("Win"));
                }
            }
        }
        return player;
    }

}
