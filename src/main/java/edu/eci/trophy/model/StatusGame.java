package edu.eci.trophy.model;

import edu.eci.trophy.persistance.MatchRepository;
import edu.eci.trophy.persistance.UserRepository;
import edu.eci.trophy.service.ApiService;
import edu.eci.trophy.service.TrophyException;
import edu.eci.trophy.service.UserService;
import edu.eci.trophy.services.impl.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatusGame implements Runnable {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApiService apiService;

    @Autowired
    UserService userService;

    private Match match;

    private final float TROPHY_COMISION = 0.20f;


    public StatusGame(Match match) {
        this.match = match;
    }

    @Override
    public void run() {
        Match matchUpdated = null;
        while (matchUpdated == null) {
            try {
                Map<Long, Map<String, Boolean>> results = apiService.getResults(match.getGameMatch().getGameId());
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
        String matchId = matchUpdated.getId();
        List<User> users = matchUpdated.getBettors();
        Map<String, Boolean> gameMatch = matchUpdated.getGameMatch().getPlayers();
        // Sincronizar, condiciones de carrera
        for (User u : users) {
            User u2 = userRepository.findByEmail(u.getEmail()); // Se repite por actualizar el usuario en la DB.
            UserBet userBet = u.getBets().get(matchId);
            UserBet userBet2 = u2.getBets().get(matchId);
            if (gameMatch.get(userBet.getPlayer())) {
                userBet.setState(true);
                userBet2.setState(true);
                matchUpdated.getWinner().add(u);
            } else {
                userBet.setState(false);
                userBet2.setState(false);
            }
            userRepository.save(u2);
        }

        // crear metodo para agregar comision de trophy
        Integer amountByUser = Math.round((matchUpdated.getPot() * (1 - TROPHY_COMISION)) / matchUpdated.getWinner().size());

        // Sincronizar, condiciones de carrera
        for (User u : users) {
            User u2 = userRepository.findByEmail(u.getEmail());
            Integer trophyPoints = u2.getTrophyPoints();
            if (u.getBets().get(matchId).isState()) {
                u.setTrophyPoints(trophyPoints + amountByUser);
                u2.setTrophyPoints(trophyPoints + amountByUser);
            } else {
                Integer discount = u.getBets().get(matchId).getBet();
                u.setTrophyPoints(trophyPoints - discount);
                u2.setTrophyPoints(trophyPoints - discount);

            }
            userRepository.save(u2);
        }

        //save match.
        matchRepository.save(matchUpdated);

        // Notificar STOMP

    }
}
