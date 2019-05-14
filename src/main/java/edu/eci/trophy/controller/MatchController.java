package edu.eci.trophy.controller;

import edu.eci.trophy.model.Game;
import edu.eci.trophy.model.Match;
import edu.eci.trophy.service.MatchService;
import edu.eci.trophy.service.TrophyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/apimatch")
@Service
@CrossOrigin("*")
public class MatchController {

    @Autowired
    MatchService matchService;

    @RequestMapping("/matcheslist")
    public ResponseEntity<?> getMatches() {
        return new ResponseEntity<>(matchService.getMatchesList(), HttpStatus.ACCEPTED);
    }

    @RequestMapping("/matcheslist/game/{game}")
    public ResponseEntity<?> getMatceshByGame(@PathVariable("game") String game) {
        ResponseEntity<?> response;
        try {
            response = new ResponseEntity<>(matchService.getMatchByGame(game), HttpStatus.ACCEPTED);
        } catch (TrophyException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, "Match controller, get match by game, param: " + game);
            response = new ResponseEntity<>("Al parecer no existen partidas con el juego : " + game, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RequestMapping("/matcheslist/mb/{minimumbet}")
    public ResponseEntity<?> getMatchesByMinimumBet(@PathVariable("minimumbet") Integer minimumBet) {
        ResponseEntity<?> response;
        try {
            response = new ResponseEntity<>(matchService.getMatchByMinimumBet(minimumBet), HttpStatus.ACCEPTED);
        } catch (TrophyException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, "Match controller, get match by minimum bet, param: " + minimumBet);
            response = new ResponseEntity<>("Al parecer no existen partidas con el monto : " + minimumBet, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RequestMapping("/match/{id}")
    public ResponseEntity<?> getMatchById(@PathVariable("id") String id) {
        ResponseEntity<?> response;
        try {
            response = new ResponseEntity<>(matchService.getMatchById(id), HttpStatus.ACCEPTED);
        } catch (TrophyException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, "Match controller, get match by id, param: " + id);
            response = new ResponseEntity<>("Al parecer no existe una partida con el ID: " + id, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<?> getMatches(@RequestBody Match match) {
        try {
            return new ResponseEntity<>(matchService.createMatch(match), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
