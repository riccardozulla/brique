package it.units.sdm.brique.model.exceptions;

public class StoneAlreadyPresentException extends RuntimeException {
    public StoneAlreadyPresentException(String message) {
        super(message);
    }
}