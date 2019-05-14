/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.trophy.controller;

import edu.eci.trophy.service.TrophyException;
import edu.eci.trophy.service.ApiService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jonathan Prieto
 */
@RestController
@RequestMapping("/apilol")
public class ApiController {

    @Autowired
    ApiService apiSer;

    @RequestMapping(method = RequestMethod.GET, path = "/{playerName}")
    public ResponseEntity<?> getPlayer(@PathVariable("playerName") String name) {
        ResponseEntity<?> response;
        try {
            response = new ResponseEntity<>(apiSer.getPlayer(name.replaceAll("\\s+", "").toLowerCase()), HttpStatus.ACCEPTED);
        } catch (TrophyException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, "Api controller, get player, param: " + name);
            response = new ResponseEntity<>("Al parecer no existe un jugador con el nombre: " + name, HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
