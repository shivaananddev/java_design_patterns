package com.anpra.java_design_patterns.patterns.behavioral.observer;

import java.util.*;

/**
 * 🏗️ Analogy: Construction Site Safety Monitoring
 * Like a safety monitoring system that notifies different departments about safety incidents
 */
public interface SafetyObserver {
    void onSafetyUpdate(SafetyEvent event);
}

public class SafetyEvent {
    private final String type;
    private final String location;
    private final String description;
    private final LocalDateTime timestamp;
    
    public SafetyEvent(String type, String location, String description) {
        this.type = type;
        this.location = location;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters
    public String getType() { return type; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }
}

public class ConstructionSiteSafety {
    private final List<SafetyObserver> observers = new ArrayList<>();
    private final List<SafetyEvent> eventLog = new ArrayList<>();
    
    public void addObserver(SafetyObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(SafetyObserver observer) {
        observers.remove(observer);
    }
    
    public void reportSafetyEvent(String type, String location, String description) {
        SafetyEvent event = new SafetyEvent(type, location, description);
        eventLog.add(event);
        notifyObservers(event);
    }
    
    private void notifyObservers(SafetyEvent event) {
        observers.forEach(observer -> observer.onSafetyUpdate(event));
    }
    
    public List<SafetyEvent> getEventLog() {
        return new ArrayList<>(eventLog);
    }
}

// Concrete Observers
public class SafetyManager implements SafetyObserver {
    @Override
    public void onSafetyUpdate(SafetyEvent event) {
        System.out.printf("Safety Manager: Reviewing safety event - %s at %s%n",
                         event.getType(), event.getLocation());
    }
}

public class EmergencyResponse implements SafetyObserver {
    @Override
    public void onSafetyUpdate(SafetyEvent event) {
        if (isEmergency(event)) {
            System.out.printf("Emergency Response: Dispatching team to %s for %s%n",
                            event.getLocation(), event.getType());
        }
    }
    
    private boolean isEmergency(SafetyEvent event) {
        return event.getType().toLowerCase().contains("emergency") ||
               event.getType().toLowerCase().contains("accident");
    }
}

public class ConstructionSupervisor implements SafetyObserver {
    private final String assignedArea;
    
    public ConstructionSupervisor(String assignedArea) {
        this.assignedArea = assignedArea;
    }
    
    @Override
    public void onSafetyUpdate(SafetyEvent event) {
        if (event.getLocation().equals(assignedArea)) {
            System.out.printf("Supervisor (%s): Addressing safety event - %s%n",
                            assignedArea, event.getDescription());
        }
    }
}
