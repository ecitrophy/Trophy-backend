package edu.eci.trophy.service;

import edu.eci.trophy.model.Match;

import java.util.List;

public interface MatchService {

    List<Match> getMatchesList();
    Match getMatchById(String id);
    List<Match> getMatchByUserId(String id);

}
