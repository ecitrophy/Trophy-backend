package edu.eci.trophy.service;

public class TrophyException extends Exception {

    public final static String NOT_FOUND = "The element does not exists";
    public final static String USER_IS_ALREADY_CREATED = "The user is alredy created";

    public TrophyException(String message) {
        super(message);
    }

    public TrophyException(String message, Throwable cause) {
        super(message, cause);
    }

}
