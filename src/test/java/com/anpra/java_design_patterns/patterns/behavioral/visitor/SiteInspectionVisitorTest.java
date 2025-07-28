package com.anpra.java_design_patterns.patterns.behavioral.visitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class SiteInspectionVisitorTest {
    private SiteInspectionVisitor inspector;
    private StructuralElement structural;
    private ElectricalSystem electrical;
    private PlumbingSystem plumbing;
    private SafetySystem safety;

    @BeforeEach
    void setUp() {
        inspector = new ComprehensiveSiteInspector("INSP-001");
        
        // Initialize test elements
        structural = new StructuralElement("STR-001", "Block A", "Column");
        electrical = new ElectricalSystem("ELE-001", "Block A", 220);
        plumbing = new PlumbingSystem("PLB-001", "Block A");
        safety = new SafetySystem("SAF-001", "Block A");
    }

    @Test
    void testStructuralInspection() {
        structural.addMetric("stress", 0.9); // Should trigger critical finding
        structural.addMetric("deflection", 0.06); // Should trigger major finding
        
        structural.accept(inspector);
        InspectionReport report = inspector.getReport();
        
        assertFalse(report.isPassed());
        assertTrue(report.hasCriticalFindings());
        assertEquals(2, report.getFindings("STR-001").size());
    }

    @Test
    void testElectricalInspection() {
        electrical.setGrounded(false); // Should trigger critical finding
        electrical.setLastMaintenanceDate(LocalDateTime.now().minusDays(100)); // Should trigger major finding
        
        electrical.accept(inspector);
        InspectionReport report = inspector.getReport();
        
        assertFalse(report.isPassed());
        assertTrue(report.hasCriticalFindings());
        assertTrue(report.getFindings("ELE-001").stream()
            .anyMatch(f -> f.severity() == InspectionReport.Severity.CRITICAL));
    }

    @Test
    void testPlumbingInspection() {
        plumbing.setPressure(30.0); // Below minimum
        plumbing.setLeakTested(false);
        
        plumbing.accept(inspector);
        InspectionReport report = inspector.getReport();
        
        assertFalse(report.isPassed());
        assertTrue(report.hasCriticalFindings());
        assertEquals(2, report.getFindings("PLB-001").size());
    }

    @Test
    void testSafetyInspection() {
        safety.setEmergencySystemsOperational(false);
        safety.setLastInspectionDate(LocalDateTime.now().minusDays(10));
        
        safety.accept(inspector);
        InspectionReport report = inspector.getReport();
        
        assertFalse(report.isPassed());
        assertTrue(report.hasCriticalFindings());
        assertTrue(report.getFindings("SAF-001").stream()
            .anyMatch(f -> f.description().contains("Emergency systems")));
    }

    @Test
    void testPassingInspection() {
        // Set up all systems to pass inspection
        structural.addMetric("stress", 0.5);
        structural.addMetric("deflection", 0.03);
        
        electrical.setGrounded(true);
        electrical.setLastMaintenanceDate(LocalDateTime.now());
        electrical.addCircuit("Main Power");
        
        plumbing.setPressure(60.0);
        plumbing.setLeakTested(true);
        plumbing.setLastPressureTest(LocalDateTime.now());
        
        safety.setEmergencySystemsOperational(true);
        safety.addSafetyEquipment("Fire Extinguisher");
        safety.addAlarm("Smoke Detector");
        safety.setLastInspectionDate(LocalDateTime.now());
        
        structural.accept(inspector);
        electrical.accept(inspector);
        plumbing.accept(inspector);
        safety.accept(inspector);
        
        InspectionReport report = inspector.getReport();
        assertTrue(report.isPassed());
        assertFalse(report.hasCriticalFindings());
    }
}
