package com.anpra.java_design_patterns.patterns.behavioral.visitor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Safety system component of a construction site
 */
public class SafetySystem implements SiteElement {
    private final String elementId;
    private final String location;
    private final List<String> safetyEquipment;
    private LocalDateTime lastInspectionDate;
    private boolean emergencySystemsOperational;
    private final List<String> alarms;

    public SafetySystem(String elementId, String location) {
        this.elementId = elementId;
        this.location = location;
        this.safetyEquipment = new ArrayList<>();
        this.alarms = new ArrayList<>();
        this.emergencySystemsOperational = false;
    }

    public void addSafetyEquipment(String equipment) {
        safetyEquipment.add(equipment);
    }

    public void addAlarm(String alarm) {
        alarms.add(alarm);
    }

    public void setLastInspectionDate(LocalDateTime date) {
        this.lastInspectionDate = date;
    }

    public void setEmergencySystemsOperational(boolean operational) {
        this.emergencySystemsOperational = operational;
    }

    public List<String> getSafetyEquipment() {
        return new ArrayList<>(safetyEquipment);
    }

    public List<String> getAlarms() {
        return new ArrayList<>(alarms);
    }

    public LocalDateTime getLastInspectionDate() {
        return lastInspectionDate;
    }

    public boolean areEmergencySystemsOperational() {
        return emergencySystemsOperational;
    }

    @Override
    public void accept(SiteInspectionVisitor visitor) {
        visitor.visitSafetySystem(this);
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getElementId() {
        return elementId;
    }
}
