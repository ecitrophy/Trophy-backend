package edu.eci.trophy.services.impl;

import edu.eci.trophy.model.User;
import edu.eci.trophy.service.TrophyException;
import edu.eci.trophy.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceStub implements UserService {

    private Map<String, User> UserMap;

    public UserServiceStub() {
        this.UserMap = new HashMap<>();
        try {
            this.createUser(new User("JuanJoAndrade", "Juan José Andrade", "juanjo@gmail.com", "qwerty"));
            this.createUser(new User("CapoTrophy", "Capo de Trophy", "capo@gmail.com", "qwerty"));
        } catch (TrophyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String mail) throws TrophyException {
        if (UserMap.containsKey(mail)) {
            return UserMap.get(mail);
        } else {
            throw new TrophyException(TrophyException.NOT_FOUND);
        }
    }

    @Override
    public User createUser(User user) throws TrophyException {
        String userMail = user.getEmail();
        if (UserMap.containsKey(userMail)) {
            throw new TrophyException(TrophyException.USER_IS_ALREADY_CREATED);
        } else {
            UserMap.put(user.getEmail(), user);
        }
        return user;
    }

    @Override
    public User updateUser(User user) {

        UserMap.replace(user.getEmail(), user);
        return user;
    }

    @Override
    public void removeUser(String mail) {
        UserMap.remove(mail);
    }

}
