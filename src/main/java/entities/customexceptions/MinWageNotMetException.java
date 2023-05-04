package entities.customexceptions;

public class MinWageNotMetException extends Exception {
    public MinWageNotMetException(String message) {
        super(message);
    }
}
