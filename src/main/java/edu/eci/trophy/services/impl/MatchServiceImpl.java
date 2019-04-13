package edu.eci.trophy.services.impl;

import edu.eci.trophy.model.Match;
import edu.eci.trophy.service.MatchService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class MatchServiceImpl implements MatchService {

    private List<Match> matchesList = new ArrayList<>();

    public MatchServiceImpl() {
        List<HashMap<String, String>> bettors =  new ArrayList<>();

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

        this.matchesList.add(new Match("LOL test-backend", "juan.gomez345", bettors, "WaitingForBets", null, 45));
    }

    @Override
    public List<Match> getMatchesList() {

        return matchesList;
    }

    @Override
    public Match getMatchById(String id) {

        return matchesList.get(Integer.parseInt(id));
    }

    @Override
    public List<Match> getMatchByUserId(String id) {
        return null;
    }

    @Override
    public void addMatch(Match m) {
        int id= matchesList.size();
        m.setId(id);
        matchesList.add(m);
    }
}
