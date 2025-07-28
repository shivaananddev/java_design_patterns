package com.anpra.java_design_patterns.patterns.behavioral.observer;

/**
 * Concrete observer for site safety supervisor
 */
public class SiteSafetySupervisor implements SafetyObserver {
    private final String siteId;
    private int totalIncidents = 0;

    public SiteSafetySupervisor(String siteId) {
        this.siteId = siteId;
    }

    @Override
    public void update(SafetyEvent event) {
        totalIncidents++;
        System.out.printf("Site %s Supervisor: Logging safety incident #%d at %s%n", 
            siteId, totalIncidents, event.getTimestamp());
        
        // Implement different responses based on severity
        switch (event.getSeverityLevel()) {
            case 9, 10 -> orderSiteEvacuation(event);
            case 7, 8 -> orderPartialEvacuation(event);
            case 5, 6 -> increaseSafetyMeasures(event);
            default -> logAndMonitor(event);
        }
    }

    private void orderSiteEvacuation(SafetyEvent event) {
        System.out.printf("EMERGENCY: Ordering immediate evacuation of site %s due to %s%n",
            siteId, event.getHazardType());
    }

    private void orderPartialEvacuation(SafetyEvent event) {
        System.out.printf("WARNING: Ordering evacuation of %s area due to %s%n",
            event.getLocation(), event.getHazardType());
    }

    private void increaseSafetyMeasures(SafetyEvent event) {
        System.out.printf("CAUTION: Implementing additional safety measures at %s%n",
            event.getLocation());
    }

    private void logAndMonitor(SafetyEvent event) {
        System.out.printf("NOTICE: Monitoring situation at %s. Current severity: %d%n",
            event.getLocation(), event.getSeverityLevel());
    }

    public int getTotalIncidents() {
        return totalIncidents;
    }
}
