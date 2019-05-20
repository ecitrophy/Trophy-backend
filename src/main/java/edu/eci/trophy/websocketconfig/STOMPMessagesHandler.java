/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.trophy.websocketconfig;

import edu.eci.trophy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Brain&Boom Team
 */
@Controller
public class STOMPMessagesHandler {

    @Autowired
    SimpMessagingTemplate msgt;
    @Autowired
    UserService userService;
    /**
     * Ingresar un usuario a una sala
     *
     * @param numroom
     * @param identifier
     * @throws Exception
     */
    @MessageMapping("/newplayer.{numroom}")
    public synchronized void handleJoinEvent(@DestinationVariable String numroom, String identifier) throws Exception {
        System.out.println("Nuevo usuario ha ingresado a la sala: " + numroom +identifier);
        
        msgt.convertAndSend("/topic/room." + numroom, userService.getUserByEmail(identifier));
    }
     /**
     * Empezar partida
     *
     * @param numroom
     * @param identifier
     * @throws Exception
     */
    @MessageMapping("/startroom.{numroom}")
    public synchronized void handleStartEvent(@DestinationVariable String numroom) throws Exception {
        System.out.println("Nuevo usuario ha ingresado a la sala: " + numroom );
        
        msgt.convertAndSend("/topic/startroom." + numroom, true);
    }



}
