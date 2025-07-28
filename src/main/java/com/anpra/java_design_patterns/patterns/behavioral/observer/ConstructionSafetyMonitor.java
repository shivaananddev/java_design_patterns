package com.anpra.java_design_patterns.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Concrete implementation of the SafetySubject interface
 * Uses thread-safe collection for observers
 */
public class ConstructionSafetyMonitor implements SafetySubject {
    private final List<SafetyObserver> observers;
    private SafetyEvent currentEvent;

    public ConstructionSafetyMonitor() {
        // Using CopyOnWriteArrayList for thread-safety
        this.observers = new CopyOnWriteArrayList<>();
    }

    @Override
    public void registerObserver(SafetyObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(SafetyObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (SafetyObserver observer : observers) {
            observer.update(currentEvent);
        }
    }

    public void reportSafetyEvent(String location, String hazardType, int severityLevel) {
        this.currentEvent = new SafetyEvent(location, hazardType, severityLevel);
        notifyObservers();
    }
}
