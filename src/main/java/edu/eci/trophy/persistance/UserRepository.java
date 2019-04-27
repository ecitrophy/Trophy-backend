package edu.eci.trophy.persistance;

import edu.eci.trophy.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Jonathan Prieto
 */
public interface UserRepository extends MongoRepository<User, String> {

    public User findByUserName(String userName);

}
