package edu.eci.trophy.services.impl;

import edu.eci.trophy.model.*;
import edu.eci.trophy.persistance.MatchRepository;
import edu.eci.trophy.persistance.UserRepository;
import edu.eci.trophy.service.ApiService;
import edu.eci.trophy.service.MatchService;
import edu.eci.trophy.service.TrophyException;
import edu.eci.trophy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MatchServiceImpl implements MatchService {

//        private int nucleosProcesamiento = Runtime.getRuntime().availableProcessors();
    private ExecutorService es = Executors.newFixedThreadPool(10);

    @Autowired
    MatchRepository matchRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ApiService apiService;

    @Autowired
    UserService userService;


    @Override
    public List<Match> getMatchesList() {
        return matchRepo.findAll();
    }

    @Override
    public List<Match> getMatchByGame(String game) throws TrophyException {
        try {
            game = game.toUpperCase();
            Game.valueOf(game);
            return matchRepo.findByGame(game);
        } catch (Exception e) {
            Logger.getLogger(MatchServiceImpl.class.getName()).log(Level.SEVERE, "MatchServiceImpl, getMatchByGame, param: " + game);
            throw new TrophyException("El juego : " + game + " no existe.");
        }
    }

    @Override
    public List<Match> getMatchByMinimumBet(Integer minimumBet) throws TrophyException {
        try {
            return matchRepo.findByMinimumBet(minimumBet);
        } catch (Exception e) {
            Logger.getLogger(MatchServiceImpl.class.getName()).log(Level.SEVERE, "MatchServiceImpl, getMatchByMinimumBet, param: " + minimumBet);
            throw new TrophyException("No hay apuestas con el monto de: " + minimumBet + " TP");
        }
    }

    @Override
    public Optional<Match> getMatchById(String id) throws TrophyException {
        try {
            return matchRepo.findById(id);
        } catch (Exception e) {
            Logger.getLogger(MatchServiceImpl.class.getName()).log(Level.SEVERE, "MatchServiceImpl, getMatchById, param: " + id);
            throw new TrophyException("La partida con el Id: " + id + " no existe.");
        }
    }

    @Override
    public Match createMatch(Match match) throws TrophyException {
        apiService.getPlayer(match.getCreator().getBets().get("default").getPlayer());
        matchRepo.save(match);
        User usr = match.getCreator();
        String matchId = match.getId();
        match.setCreator(updateUserBetsId(matchId, usr));
        match.getBettors().add(match.getCreator());
        match.setPot(match.getPot() + usr.getBets().get(matchId).getBet());
        matchRepo.save(match);
        return match;
    }

    @Override
    public boolean playMatch(String id) throws TrophyException {
        Match match = getMatchById(id).get();
        GameMatch gameMatch = apiService.isPlaying(match.getCreator().getBets().get(match.getId()).getPlayer());
        long gameStartTime = gameMatch.getGameStartTime();
        System.out.println(new Timestamp(System.currentTimeMillis()).getTime());
        if (match.getBettors().size() < 2 || gameStartTime == 0 || gameStartTime + 300000 <= new Timestamp(System.currentTimeMillis()).getTime()) {
            throw new TrophyException("Cantidad de apostadores no suficiente, la partida aun no ha empezado o ya se vencio el tiempo para hacer apuestas");
        }
        List<User> bettors = match.getBettors();
        Set<String> players = gameMatch.getPlayers().keySet();
        for (User u : bettors) {
            String player = u.getBets().get(match.getId()).getPlayer();
            if (!players.contains(player)) {
                throw new TrophyException("El jugador: " + player + " No esta en la partida");
            }
        }
        match.setGameMatch(gameMatch);
        match.setState(MatchStatus.INGAME);
        matchRepo.save(match);
        es.execute(new StatusGame(match));
        return true;
    }

    @Override
    public boolean addUser(String id, User user) throws TrophyException, Exception {
        Match match = getMatchById(id).get();
        Integer mb = match.getMinimumBet();
        Integer userBet = user.getBets().get("default").getBet();
        if (userBet < mb) {
            throw new TrophyException("Su apuesta debe ser mayor o igual a: " + mb);
        }
        for (User u : match.getBettors()) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new TrophyException("El usuario con el nombre o correo" + u.getEmail() + " ya existe.");
            }

        }
        user = updateUserBetsId(id, user);
        match.getBettors().add(user);
        match.setPot(match.getPot() + userBet);
        matchRepo.save(match);
        return true;
    }

    private User updateUserBetsId(String id, User u) throws TrophyException {
        Map<String, UserBet> userBets = u.getBets();
        UserBet ub = userBets.remove("default");
        ub.setId(id);
        u = userService.getUserByEmail(u.getEmail());
        userBets = u.getBets();
        userBets.put(id, ub);
        userRepo.save(u);
        return u;
    }

}
 