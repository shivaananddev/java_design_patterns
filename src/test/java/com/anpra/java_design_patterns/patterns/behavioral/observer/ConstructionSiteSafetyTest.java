package com.anpra.java_design_patterns.patterns.behavioral.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConstructionSiteSafetyTest {
    private ConstructionSiteSafety siteSafety;
    private SafetyManager safetyManager;
    private EmergencyResponse emergencyResponse;
    private ConstructionSupervisor supervisor;
    
    @BeforeEach
    void setUp() {
        siteSafety = new ConstructionSiteSafety();
        safetyManager = mock(SafetyManager.class);
        emergencyResponse = mock(EmergencyResponse.class);
        supervisor = mock(ConstructionSupervisor.class);
    }
    
    @Test
    void testSafetyEventNotification() {
        siteSafety.addObserver(safetyManager);
        siteSafety.reportSafetyEvent("Hazard", "Zone A", "Exposed wiring");
        
        verify(safetyManager).onSafetyUpdate(any(SafetyEvent.class));
    }
    
    @Test
    void testMultipleObservers() {
        siteSafety.addObserver(safetyManager);
        siteSafety.addObserver(emergencyResponse);
        
        siteSafety.reportSafetyEvent("Emergency", "Zone B", "Fire outbreak");
        
        verify(safetyManager).onSafetyUpdate(any(SafetyEvent.class));
        verify(emergencyResponse).onSafetyUpdate(any(SafetyEvent.class));
    }
    
    @Test
    void testRemoveObserver() {
        siteSafety.addObserver(safetyManager);
        siteSafety.removeObserver(safetyManager);
        
        siteSafety.reportSafetyEvent("Minor", "Zone C", "Spill");
        
        verify(safetyManager, never()).onSafetyUpdate(any(SafetyEvent.class));
    }
    
    @Test
    void testEventLog() {
        siteSafety.reportSafetyEvent("Hazard", "Zone A", "Test event 1");
        siteSafety.reportSafetyEvent("Minor", "Zone B", "Test event 2");
        
        assertEquals(2, siteSafety.getEventLog().size());
    }
}
