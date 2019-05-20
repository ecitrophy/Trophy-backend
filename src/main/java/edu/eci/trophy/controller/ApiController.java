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
import org.springframework.web.bind.annotation.*;

/**
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
            response = new ResponseEntity<>(apiSer.getPlayer(name), HttpStatus.ACCEPTED);
        } catch (TrophyException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, "Api controller, get player, param: " + name, ex);
            response = new ResponseEntity<>("No existe un jugador con el nombre: " + name, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/isplaying/{playerName}")
    public ResponseEntity<?> isPlaying(@PathVariable("playerName") String name) {
        ResponseEntity<?> response;
        try {
            response = new ResponseEntity<>(apiSer.isPlaying(name.replaceAll("\\s+", "").toLowerCase()), HttpStatus.ACCEPTED);
        } catch (TrophyException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, "Api controller, is playing, param: " + name, ex);
            response = new ResponseEntity<>("El jugador : " + name + " no esta jugando o no existe", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/results/{gameId}")
    public ResponseEntity<?> getResults(@PathVariable("gameId") Integer gameId) {
        ResponseEntity<?> response;
        try {
            response = new ResponseEntity<>(apiSer.getResults(gameId), HttpStatus.ACCEPTED);
        } catch (TrophyException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, "Api controller, get results, param: " + gameId, ex);
            response = new ResponseEntity<>("La partida : " + gameId + " no existe", HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
