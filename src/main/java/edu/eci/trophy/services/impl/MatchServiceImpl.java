package edu.eci.trophy.services.impl;

import edu.eci.trophy.model.Game;
import edu.eci.trophy.model.Match;
import edu.eci.trophy.model.MatchStatus;
import edu.eci.trophy.model.User;
import edu.eci.trophy.persistance.MatchRepository;
import edu.eci.trophy.service.MatchService;
import edu.eci.trophy.service.TrophyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    MatchRepository matchRepo;

    //private List<Match> matchesList = new ArrayList<>();

    /*public MatchServiceImpl() {
        List<HashMap<String, String>> bettors = new ArrayList<>();

        HashMap<String, String> bettor = new HashMap<>();
        bettor.put("username", "test-backend1");
        bettor.put("bet", "15");
        HashMap<String, String> bettor2 = new HashMap<>();
        bettor2.put("username", "test-backend2");
        bettor2.put("bet", "15");

        HashMap<String, String> bettor3 = new HashMap<>();
        bettor3.put("username", "test-backend3");
        bettor3.put("bet", "15");


        bettors.add(bettor);
        bettors.add(bettor2);
        bettors.add(bettor3);

        this.matchesList.add(new Match("LOL test-backend", Game.LEAGUEOFLEGENDS, 5000, new User("TestUser1", "Test User", "test@trophy.com", "ieti2019"), MatchStatus.OPEN));
    }*/

    @Override
    public List<Match> getMatchesList() {
        return matchRepo.findAll();
    }

    @Override
    public List<Match> getMatchByGame(String game) throws TrophyException {
        try {
            Game.valueOf(game.toUpperCase());
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
    public Match createMatch(Match match) {
        matchRepo.save(match);
        return match;
    }
}
