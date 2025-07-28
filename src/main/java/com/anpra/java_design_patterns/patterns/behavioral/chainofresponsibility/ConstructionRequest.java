package com.anpra.java_design_patterns.patterns.behavioral.chainofresponsibility;

/**
 * Construction request class containing approval details
 */
public class ConstructionRequest {
    private final String projectId;
    private final String description;
    private final double estimatedCost;
    private final int complexityLevel;
    private final boolean requiresPermits;

    public ConstructionRequest(String projectId, String description, double estimatedCost, 
                             int complexityLevel, boolean requiresPermits) {
        this.projectId = projectId;
        this.description = description;
        this.estimatedCost = estimatedCost;
        this.complexityLevel = complexityLevel;
        this.requiresPermits = requiresPermits;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getDescription() {
        return description;
    }

    public double getEstimatedCost() {
        return estimatedCost;
    }

    public int getComplexityLevel() {
        return complexityLevel;
    }

    public boolean requiresPermits() {
        return requiresPermits;
    }
}
