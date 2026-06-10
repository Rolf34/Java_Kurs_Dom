package ua.acity.observer;

import java.time.LocalDateTime;

public class CityEvent {
    private EventType type;
    private String message;
    private LocalDateTime timestamp;

    public CityEvent(EventType type, String message) {
        this.type = type;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public EventType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
