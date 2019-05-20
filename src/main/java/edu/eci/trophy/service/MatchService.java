package edu.eci.trophy.service;

import edu.eci.trophy.model.Match;
import edu.eci.trophy.model.User;

import java.util.List;
import java.util.Optional;

public interface MatchService {

    List<Match> getMatchesList();

    List<Match> getMatchByGame(String game) throws TrophyException;

    List<Match> getMatchByMinimumBet(Integer minimumBet) throws TrophyException;

    Optional<Match> getMatchById(String id) throws TrophyException;

    Match createMatch(Match m) throws TrophyException;

    boolean playMatch(String id) throws TrophyException;

    boolean addUser(String id, User user) throws TrophyException, Exception;

}
