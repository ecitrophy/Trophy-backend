package edu.eci.trophy.trophybackend.services;



import edu.eci.trophy.trophybackend.models.User;
import java.util.List;


public interface UserService {

    

    User getUser(String username) throws TrophyException;

    User createUser(User user) throws TrophyException;

    User updateUser(User user);

    void removeUser(String userId);

}