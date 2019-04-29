package edu.eci.trophy.service;

import edu.eci.trophy.model.User;

public interface UserService {

    User getUserByUserName(String userName);

    User getUserByEmail(String email) throws TrophyException;

    User createUser(User newUser) throws TrophyException;

    User updateUser(User user) throws TrophyException;

    void removeUser(String userId);



}
