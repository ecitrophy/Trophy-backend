package edu.eci.trophy.service;

import edu.eci.trophy.model.User;

public interface UserService {

    User getUser(String username) throws TrophyException;

    User createUser(User user) throws TrophyException;

    User updateUser(User user);

    void removeUser(String userId);

}
