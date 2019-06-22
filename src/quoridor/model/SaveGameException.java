package quoridor.model;

public class SaveGameException extends Exception { 
    public SaveGameException(String errorMessage) {
        super(errorMessage);
    }
}