package edu.eci.trophy.persistance;

import edu.eci.trophy.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Jonathan Prieto
 */
public interface PlayerRepository extends MongoRepository<Player, String> {

    Player findByName(String name);
}
