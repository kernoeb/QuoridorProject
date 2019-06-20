package quoridor;

public class SaveGameException extends Exception { 
    public SaveGameException(String errorMessage) {
        super(errorMessage);
    }
}