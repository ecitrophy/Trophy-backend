package edu.eci.trophy.trophybackend.services;

import edu.eci.trophy.trophybackend.models.Match;

import java.util.List;

public interface MatchService {

    List<Match> getMatchesList();
    Match getMatchById(String id);
    List<Match> getMatchByUserId(String id);
    void addMatch(Match m);

}
