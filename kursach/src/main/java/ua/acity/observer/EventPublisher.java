package ua.acity.observer;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {
    private List<CityObserver> observers = new ArrayList<>();

    public void subscribe(CityObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void unsubscribe(CityObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(CityEvent event) {
        for (CityObserver observer : observers) {
            observer.update(event);
        }
    }
}
