package edu.eci.trophy.trophybackend.services.impl;

import edu.eci.trophy.trophybackend.models.Match;
import edu.eci.trophy.trophybackend.services.MatchService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class MatchServiceImpl implements MatchService {

    private List<Match> matchesList = new ArrayList<>();

    public MatchServiceImpl() {
        HashMap<String, Integer> bettors = new HashMap<>();
        bettors.put("user1",15);
        bettors.put("user2",0);
        bettors.put("user3",15);
        bettors.put("user4",15);
        bettors.put("user5",15);
        this.matchesList.add(new Match("LOL Match","juan.gomez345", bettors, "WaitingForBets", null, 1   ));
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
        matchesList.add(m);
    }
}
