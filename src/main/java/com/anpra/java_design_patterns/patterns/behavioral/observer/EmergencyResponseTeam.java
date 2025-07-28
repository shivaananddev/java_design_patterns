package com.anpra.java_design_patterns.patterns.behavioral.observer;

/**
 * Concrete observer for emergency response team
 */
public class EmergencyResponseTeam implements SafetyObserver {
    private final String teamId;

    public EmergencyResponseTeam(String teamId) {
        this.teamId = teamId;
    }

    @Override
    public void update(SafetyEvent event) {
        if (event.getSeverityLevel() >= 8) {
            System.out.printf("CRITICAL: Team %s dispatching emergency response to %s. Hazard: %s%n",
                teamId, event.getLocation(), event.getHazardType());
        } else if (event.getSeverityLevel() >= 5) {
            System.out.printf("URGENT: Team %s preparing for potential deployment to %s. Hazard: %s%n",
                teamId, event.getLocation(), event.getHazardType());
        } else {
            System.out.printf("ALERT: Team %s monitoring situation at %s. Hazard: %s%n",
                teamId, event.getLocation(), event.getHazardType());
        }
    }
}
