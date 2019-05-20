package edu.eci.trophy.model;

import edu.eci.trophy.service.ApiService;
import edu.eci.trophy.service.TrophyException;
import edu.eci.trophy.services.impl.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatusGame implements Runnable {

    @Autowired
    ApiService as;

    Match match;

    public StatusGame(Match match) {
        this.match = match;
    }

    @Override
    public void run() {
        Match matchUpdated = null;
        while (matchUpdated == null) {
            try {
                Map<Long, Map<String, Boolean>> results = as.getResults(match.getGameMatch().getGameId());
                for (long l : results.keySet()) {
                    match.getGameMatch().setGameDuration(l);
                    match.getGameMatch().setPlayers(results.get(l));
                }
                matchUpdated = match;
            } catch (TrophyException e) {
                Logger.getLogger(MatchServiceImpl.class.getName()).log(Level.INFO, "La partida no ha finalizado aun");
            }
            if (matchUpdated == null) {
                try {
                    Thread.sleep(120000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StatusGame.class.getName()).log(Level.SEVERE, "Status Game Class, run method", ex);
                }
            }
        }
        //update trophypoints.
        //save match.
    }
}
