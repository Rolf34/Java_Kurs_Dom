package ua.acity.observer;

import java.util.HashMap;
import java.util.Map;

public class StatisticsObserver implements CityObserver {
    private Map<EventType, Integer> statistics = new HashMap<>();

    @Override
    public void update(CityEvent event) {
        EventType type = event.getType();
        statistics.put(type, statistics.getOrDefault(type, 0) + 1);
        System.out.println("Statistics updated: " + type + " count = " + statistics.get(type));
    }

    public Map<EventType, Integer> getStatistics() {
        return statistics;
    }
}
