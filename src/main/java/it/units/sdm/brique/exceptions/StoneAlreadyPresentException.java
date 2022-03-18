package it.units.sdm.brique.exceptions;

public class StoneAlreadyPresentException extends RuntimeException{
    public StoneAlreadyPresentException(String message){
        super(message);
    }
}