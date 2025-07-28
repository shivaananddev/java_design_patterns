package com.anpra.java_design_patterns.patterns.behavioral.observer;

import java.time.LocalDateTime;

/**
 * Event class containing safety-related information
 */
public class SafetyEvent {
    private final String location;
    private final String hazardType;
    private final int severityLevel;
    private final LocalDateTime timestamp;

    public SafetyEvent(String location, String hazardType, int severityLevel) {
        this.location = location;
        this.hazardType = hazardType;
        this.severityLevel = severityLevel;
        this.timestamp = LocalDateTime.now();
    }

    public String getLocation() {
        return location;
    }

    public String getHazardType() {
        return hazardType;
    }

    public int getSeverityLevel() {
        return severityLevel;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("SafetyEvent[location=%s, hazard=%s, severity=%d, time=%s]",
            location, hazardType, severityLevel, timestamp);
    }
}
