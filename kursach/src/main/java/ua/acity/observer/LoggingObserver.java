package ua.acity.observer;

import java.time.format.DateTimeFormatter;

public class LoggingObserver implements CityObserver {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void update(CityEvent event) {
        String logMessage = String.format("[%s] [%s] %s",
                event.getTimestamp().format(formatter),
                event.getType(),
                event.getMessage());
        System.out.println(logMessage);
    }
}
