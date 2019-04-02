/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.trophy.persistance;

import edu.eci.trophy.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Jonathan Prieto
 */
public interface PlayerRepository extends MongoRepository<Player, String> {

    public Player findByName(String name);
}
