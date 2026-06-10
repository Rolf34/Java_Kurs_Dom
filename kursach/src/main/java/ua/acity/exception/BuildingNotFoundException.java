package ua.acity.exception;

public class BuildingNotFoundException extends Exception {
    public BuildingNotFoundException(String message) {
        super(message);
    }

    public BuildingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
