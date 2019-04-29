package edu.eci.trophy.services.impl;

import edu.eci.trophy.model.User;
import edu.eci.trophy.persistance.UserRepository;
import edu.eci.trophy.service.TrophyException;
import edu.eci.trophy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    private Map<String, User> UserMap;

//    public UserServiceImpl() {
//        this.UserMap = new HashMap<>();
//        try {
//            this.createUser(new User("JuanJoAndrade", "Juan José Andrade", "juanjo@gmail.com", "qwerty", 1450));
//            this.createUser(new User("CapoTrophy", "Capo de Trophy", "capo@gmail.com", "qwerty", 999999999));
//        } catch (TrophyException e) {
//            e.printStackTrace();
//        }
//    }

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
        if (UserMap.containsKey(email)) {
            return UserMap.get(email);
        } else {
            throw new TrophyException(TrophyException.NOT_FOUND);
        }
    }

    @Override
    public User createUser(User newUser) throws TrophyException {
        System.out.println(newUser.toString());
        String userName = newUser.getUserName();
        if (getUserByUserName(userName) != null) {
            throw new TrophyException("El usuario: " + userName + " ya existe.");
        }
        userRepo.save(newUser);
        System.out.println(newUser.toString());
        return newUser;
    }

    @Override
    public User updateUser(User user) throws TrophyException {
        if (UserMap.containsKey(user.getEmail())) {

            user.setPassword(UserMap.get(user.getEmail()).getPassword());
        } else {
            throw new TrophyException(TrophyException.NOT_FOUND);
        }
        UserMap.replace(user.getEmail(), user);
        return user;
    }

    @Override
    public void removeUser(String mail) {
        UserMap.remove(mail);
    }

}
