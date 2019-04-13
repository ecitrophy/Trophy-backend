package edu.eci.trophy.controller;

import edu.eci.trophy.model.User;
import edu.eci.trophy.service.TrophyException;
import edu.eci.trophy.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping("/user")

@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Token login(@RequestBody User login)
            throws ServletException {

        String jwtToken;

        if (login.getEmail() == null || login.getPassword() == null) {
            throw new ServletException("Please fill in email and password");
        }

        String email = login.getEmail();
        String password = login.getPassword();

        User user;
        try {
            user = userService.getUser(email);

        } catch (TrophyException e) {
            throw new ServletException("User email not found.");
        }

        String pwd = user.getPassword();

        if (!password.equals(pwd)) {
            throw new ServletException("Invalid login. Please check your name and password.");
        }
        //
        jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date()).signWith(
                SignatureAlgorithm.HS256, "secretkey").compact();

        return new Token(jwtToken);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestBody User register)
            throws ServletException {
        if (register.getEmail() != null && register.getName() != null && register.getPassword() != null && register.getUserName() != null) {
            try {
                userService.createUser(register);
            } catch (Exception e) {
                throw new ServletException("Invalid data while creation user");
            }
        }

    }

    public class Token {

        String accessToken;

        public Token(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String access_token) {
            this.accessToken = access_token;
        }
    }
}
