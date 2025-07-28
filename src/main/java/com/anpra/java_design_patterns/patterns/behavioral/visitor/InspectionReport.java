package com.anpra.java_design_patterns.patterns.behavioral.visitor;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Class representing an inspection report
 */
public class InspectionReport {
    private final String inspectorId;
    private final LocalDateTime inspectionTime;
    private final Map<String, List<Finding>> findings;
    private String summary;
    private boolean passed;

    public InspectionReport(String inspectorId) {
        this.inspectorId = inspectorId;
        this.inspectionTime = LocalDateTime.now();
        this.findings = new HashMap<>();
    }

    public void addFinding(String elementId, String description, Severity severity) {
        findings.computeIfAbsent(elementId, k -> new ArrayList<>())
                .add(new Finding(description, severity));
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setPassedStatus(boolean passed) {
        this.passed = passed;
    }

    public List<Finding> getFindings(String elementId) {
        return findings.getOrDefault(elementId, Collections.emptyList());
    }

    public Map<String, List<Finding>> getAllFindings() {
        return Collections.unmodifiableMap(findings);
    }

    public boolean hasCriticalFindings() {
        return findings.values().stream()
                .flatMap(List::stream)
                .anyMatch(f -> f.severity == Severity.CRITICAL);
    }

    public String getInspectorId() {
        return inspectorId;
    }

    public LocalDateTime getInspectionTime() {
        return inspectionTime;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isPassed() {
        return passed;
    }

    public record Finding(String description, Severity severity) {}

    public enum Severity {
        MINOR, MAJOR, CRITICAL
    }

    @Override
    public String toString() {
        StringBuilder report = new StringBuilder();
        report.append("Inspection Report\n");
        report.append("================\n");
        report.append(String.format("Inspector: %s\n", inspectorId));
        report.append(String.format("Time: %s\n", inspectionTime));
        report.append(String.format("Status: %s\n", passed ? "PASSED" : "FAILED"));
        report.append("\nFindings:\n");
        
        findings.forEach((elementId, elementFindings) -> {
            report.append(String.format("\nElement: %s\n", elementId));
            elementFindings.forEach(finding -> 
                report.append(String.format("- [%s] %s\n", 
                    finding.severity(), finding.description())));
        });
        
        report.append("\nSummary: ").append(summary);
        return report.toString();
    }
}
