/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.trophy.model;

/**
 *
 * @author Jonathan Prieto
 */
public class TrophyException extends Exception {

    public TrophyException(String message) {
        super(message);
    }

    public TrophyException(String message, Throwable cause) {
        super(message, cause);
    }

}
