package edu.eci.trophy.controller;

import edu.eci.trophy.model.Match;
import edu.eci.trophy.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Service
@CrossOrigin("*")
public class MatchController {

    @Autowired
    private final MatchService matchServiceService;

    public MatchController(MatchService matchService) {

        this.matchServiceService = matchService;
    }

    @RequestMapping("/matcheslist")
    public List<Match> getMatches() {
        return matchServiceService.getMatchesList();
    }

    @PostMapping("/matcheslist")
    public ResponseEntity<?> getMatches(@RequestBody Match match) {
        try {
            matchServiceService.addMatch(match);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
