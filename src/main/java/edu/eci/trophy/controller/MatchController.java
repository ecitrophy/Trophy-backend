package edu.eci.trophy.controller;

import edu.eci.trophy.model.Match;
import edu.eci.trophy.service.MatchService;
import edu.eci.trophy.service.UserService;
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
