package edu.eci.trophy.controller;

import edu.eci.trophy.model.User;
import edu.eci.trophy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, path = "/{userName}")
    public ResponseEntity<?> getUserByUserName(@PathVariable("userName") String userName) {
        ResponseEntity<?> response;
        try {
            response = new ResponseEntity<>(userService.getUserByUserName(userName), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = new ResponseEntity<>("Al parecer no existe un usuario con el nombre: " + userName, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RequestMapping("/email/{id}")
    public User getUser(@PathVariable("id") String id) {
        try{
            return userService.getUserByEmail(id);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        try {
            userService.removeUser(email);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
