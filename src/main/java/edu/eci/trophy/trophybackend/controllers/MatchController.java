package edu.eci.trophy.trophybackend.controllers;

import edu.eci.trophy.trophybackend.models.Match;
import edu.eci.trophy.trophybackend.services.MatchService;
import edu.eci.trophy.trophybackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Service
@CrossOrigin(origins = "http://localhost:3000")
public class MatchController {


    @Autowired
    private final MatchService matchServiceService;

    public MatchController( MatchService matchService) {

        this.matchServiceService = matchService;
    }

    @RequestMapping("/matcheslist")
    public List<Match> getMatches(){
        return matchServiceService.getMatchesList();
    }
}
