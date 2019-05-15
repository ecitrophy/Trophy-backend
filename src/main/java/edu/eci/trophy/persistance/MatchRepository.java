package edu.eci.trophy.persistance;

import edu.eci.trophy.model.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MatchRepository extends MongoRepository<Match, String> {

    List<Match> findByGame(String game);

    List<Match> findByMinimumBet(Integer minimumBet);
}
