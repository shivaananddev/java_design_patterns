package com.anpra.java_design_patterns.patterns.behavioral.observer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class SafetyMonitoringTest {
    private ConstructionSafetyMonitor safetyMonitor;
    private SiteSafetySupervisor supervisor;
    private EmergencyResponseTeam responseTeam;

    @BeforeEach
    void setUp() {
        safetyMonitor = new ConstructionSafetyMonitor();
        supervisor = new SiteSafetySupervisor("TEST-SITE");
        responseTeam = new EmergencyResponseTeam("TEST-TEAM");
    }

    @Test
    void testObserverRegistration() {
        safetyMonitor.registerObserver(supervisor);
        safetyMonitor.registerObserver(responseTeam);
        
        // Report an event and verify incident count
        safetyMonitor.reportSafetyEvent("Test Location", "Test Hazard", 5);
        assertEquals(1, supervisor.getTotalIncidents());
    }

    @Test
    void testObserverRemoval() {
        safetyMonitor.registerObserver(supervisor);
        safetyMonitor.registerObserver(responseTeam);
        
        safetyMonitor.reportSafetyEvent("Location 1", "Hazard 1", 3);
        assertEquals(1, supervisor.getTotalIncidents());

        safetyMonitor.removeObserver(supervisor);
        safetyMonitor.reportSafetyEvent("Location 2", "Hazard 2", 4);
        
        // Verify supervisor didn't receive the second event
        assertEquals(1, supervisor.getTotalIncidents());
    }

    @Test
    void testDuplicateRegistration() {
        safetyMonitor.registerObserver(supervisor);
        safetyMonitor.registerObserver(supervisor); // Attempt duplicate registration
        
        safetyMonitor.reportSafetyEvent("Test Location", "Test Hazard", 1);
        assertEquals(1, supervisor.getTotalIncidents()); // Should only receive one notification
    }

    @Test
    void testNullObserver() {
        assertDoesNotThrow(() -> {
            safetyMonitor.registerObserver(null);
            safetyMonitor.removeObserver(null);
        });
    }

    @Test
    void testSeverityLevels() {
        safetyMonitor.registerObserver(supervisor);
        
        // Test different severity levels
        safetyMonitor.reportSafetyEvent("Location", "Minor", 2);
        safetyMonitor.reportSafetyEvent("Location", "Moderate", 6);
        safetyMonitor.reportSafetyEvent("Location", "Severe", 9);
        
        assertEquals(3, supervisor.getTotalIncidents());
    }
}
