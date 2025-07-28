package com.anpra.java_design_patterns.patterns.behavioral.visitor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Concrete visitor for comprehensive site inspection
 */
public class ComprehensiveSiteInspector implements SiteInspectionVisitor {
    private final InspectionReport report;
    private static final int MAX_DAYS_SINCE_MAINTENANCE = 90;
    private static final double MIN_PRESSURE = 40.0;
    private static final double MAX_PRESSURE = 80.0;

    public ComprehensiveSiteInspector(String inspectorId) {
        this.report = new InspectionReport(inspectorId);
    }

    @Override
    public void visitStructuralElement(StructuralElement element) {
        // Check structural integrity metrics
        Map<String, Double> metrics = element.getAllMetrics();
        
        metrics.forEach((metric, value) -> {
            if ("stress".equals(metric) && value > 0.8) {
                report.addFinding(element.getElementId(),
                    "High stress level detected: " + value,
                    InspectionReport.Severity.CRITICAL);
            }
            if ("deflection".equals(metric) && value > 0.05) {
                report.addFinding(element.getElementId(),
                    "Excessive deflection detected: " + value,
                    InspectionReport.Severity.MAJOR);
            }
        });
    }

    @Override
    public void visitElectricalSystem(ElectricalSystem system) {
        // Check grounding
        if (!system.isGrounded()) {
            report.addFinding(system.getElementId(),
                "System not properly grounded",
                InspectionReport.Severity.CRITICAL);
        }

        // Check maintenance schedule
        if (system.getLastMaintenanceDate() != null) {
            long daysSinceMaintenance = ChronoUnit.DAYS.between(
                system.getLastMaintenanceDate(), LocalDateTime.now());
            if (daysSinceMaintenance > MAX_DAYS_SINCE_MAINTENANCE) {
                report.addFinding(system.getElementId(),
                    "Maintenance overdue by " + (daysSinceMaintenance - MAX_DAYS_SINCE_MAINTENANCE) + " days",
                    InspectionReport.Severity.MAJOR);
            }
        }

        // Check circuits
        if (system.getCircuits().isEmpty()) {
            report.addFinding(system.getElementId(),
                "No circuits documented",
                InspectionReport.Severity.MAJOR);
        }
    }

    @Override
    public void visitPlumbingSystem(PlumbingSystem system) {
        // Check pressure levels
        double pressure = system.getPressure();
        if (pressure < MIN_PRESSURE || pressure > MAX_PRESSURE) {
            report.addFinding(system.getElementId(),
                "Pressure outside acceptable range: " + pressure,
                InspectionReport.Severity.MAJOR);
        }

        // Check leak testing
        if (!system.isLeakTested()) {
            report.addFinding(system.getElementId(),
                "System not leak tested",
                InspectionReport.Severity.CRITICAL);
        }

        // Check pressure test date
        if (system.getLastPressureTest() != null) {
            long daysSinceTest = ChronoUnit.DAYS.between(
                system.getLastPressureTest(), LocalDateTime.now());
            if (daysSinceTest > 30) {
                report.addFinding(system.getElementId(),
                    "Pressure test overdue",
                    InspectionReport.Severity.MAJOR);
            }
        }
    }

    @Override
    public void visitSafetySystem(SafetySystem system) {
        // Check emergency systems
        if (!system.areEmergencySystemsOperational()) {
            report.addFinding(system.getElementId(),
                "Emergency systems non-operational",
                InspectionReport.Severity.CRITICAL);
        }

        // Check safety equipment
        if (system.getSafetyEquipment().isEmpty()) {
            report.addFinding(system.getElementId(),
                "No safety equipment registered",
                InspectionReport.Severity.CRITICAL);
        }

        // Check alarms
        if (system.getAlarms().isEmpty()) {
            report.addFinding(system.getElementId(),
                "No alarm systems registered",
                InspectionReport.Severity.CRITICAL);
        }

        // Check last inspection date
        if (system.getLastInspectionDate() != null) {
            long daysSinceInspection = ChronoUnit.DAYS.between(
                system.getLastInspectionDate(), LocalDateTime.now());
            if (daysSinceInspection > 7) {
                report.addFinding(system.getElementId(),
                    "Safety inspection overdue",
                    InspectionReport.Severity.MAJOR);
            }
        }
    }

    @Override
    public InspectionReport getReport() {
        // Finalize report
        boolean passed = !report.hasCriticalFindings();
        report.setPassedStatus(passed);
        
        StringBuilder summary = new StringBuilder();
        summary.append("Inspection complete. Status: ").append(passed ? "PASSED" : "FAILED");
        summary.append("\nTotal findings: ").append(
            report.getAllFindings().values().stream()
                  .mapToInt(List::size)
                  .sum());
        
        report.setSummary(summary.toString());
        return report;
    }
}
