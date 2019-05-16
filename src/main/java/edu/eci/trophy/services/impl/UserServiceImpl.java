package edu.eci.trophy.services.impl;

import edu.eci.trophy.model.User;
import edu.eci.trophy.persistance.UserRepository;
import edu.eci.trophy.service.TrophyException;
import edu.eci.trophy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;


    /*public UserServiceImpl() {
        this.UserMap = new HashMap<>();
        try {
            this.createUser(new User("JuanJoAndrade", "Juan José Andrade", "juanjo@gmail.com", "qwerty", 1450));
            this.createUser(new User("CapoTrophy", "Capo de Trophy", "capo@gmail.com", "qwerty", 999999999));
        } catch (TrophyException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public User getUserByUserName(String userName) {
        User user = null;
        try {
            user = userRepo.findByUserName(userName.toLowerCase());
        } catch (Exception e) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, "UserServiceImpl, getUserByUserName, param: " + userName);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws TrophyException {
        User user = null;
        try {
            user = userRepo.findByEmail(email.toLowerCase());
        } catch (Exception e) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, "UserServiceImpl, getUserByEmail, param: " + email);
        }
        return user;
    }

    @Override
    public User createUser(User newUser) throws TrophyException {
        String userName = newUser.getUserName();
        String userEmail = newUser.getEmail();
        if (getUserByUserName(userName) != null || getUserByEmail(userEmail) != null) {
            throw new TrophyException("El usuario con el nombre o correo: " + userName + " - " + userEmail + " ya existe.");
        }
        userRepo.save(newUser);
        return newUser;
    }

    @Override
    public User updateUser(User user) throws TrophyException {
        String userName = user.getUserName();
        User oldUser = getUserByUserName(userName);
        if (oldUser == null) {
            throw new TrophyException("El usuario: " + userName + " no existe.");
        }
        user.setId(oldUser.getId());
        user.setEmail(oldUser.getEmail());
        userRepo.save(user);
        return user;
    }

    @Override
    public String removeUser(String userName) throws TrophyException {
        User user = getUserByUserName(userName);
        if (user == null) {
            throw new TrophyException("El usuario: " + userName + " no existe o ya fue eliminado.");
        }
        userRepo.delete(user);
        return user.getId();
    }

}
