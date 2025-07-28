package com.anpra.java_design_patterns.patterns.behavioral.observer;

/**
 * Demo class for the Observer pattern in construction safety monitoring
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {
        // Create the safety monitoring system
        ConstructionSafetyMonitor safetyMonitor = new ConstructionSafetyMonitor();

        // Create observers
        SiteSafetySupervisor supervisor = new SiteSafetySupervisor("SITE-001");
        EmergencyResponseTeam team1 = new EmergencyResponseTeam("ERT-A");
        EmergencyResponseTeam team2 = new EmergencyResponseTeam("ERT-B");

        // Register observers
        safetyMonitor.registerObserver(supervisor);
        safetyMonitor.registerObserver(team1);
        safetyMonitor.registerObserver(team2);

        // Simulate different safety events
        System.out.println("=== Testing Safety Monitoring System ===\n");
        
        // Minor incident
        safetyMonitor.reportSafetyEvent("North Wing", "Minor Debris", 3);
        System.out.println();

        // Moderate hazard
        safetyMonitor.reportSafetyEvent("East Tower", "Equipment Malfunction", 6);
        System.out.println();

        // Severe situation
        safetyMonitor.reportSafetyEvent("Main Site", "Structural Instability", 9);
        System.out.println();

        // Remove one team and test again
        safetyMonitor.removeObserver(team2);
        System.out.println("=== After removing ERT-B ===\n");
        
        safetyMonitor.reportSafetyEvent("South Wing", "Gas Leak", 8);
        
        System.out.println("\nTotal incidents recorded by supervisor: " + supervisor.getTotalIncidents());
    }
}
