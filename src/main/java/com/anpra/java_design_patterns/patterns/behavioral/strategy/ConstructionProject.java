package com.anpra.java_design_patterns.patterns.behavioral.strategy;

/**
 * Context class that uses the cost calculation strategy
 */
public class ConstructionProject {
    private CostCalculationStrategy costStrategy;
    private final String projectName;
    private final double squareFootage;
    private final String location;

    public ConstructionProject(String projectName, double squareFootage, String location) {
        this.projectName = projectName;
        this.squareFootage = squareFootage;
        this.location = location;
    }

    public void setStrategy(CostCalculationStrategy strategy) {
        this.costStrategy = strategy;
    }

    public double calculateProjectCost(boolean isRushOrder) {
        if (costStrategy == null) {
            throw new IllegalStateException("Cost calculation strategy not set");
        }
        return costStrategy.calculateCost(squareFootage, location, isRushOrder);
    }

    public String getProjectName() {
        return projectName;
    }

    public double getSquareFootage() {
        return squareFootage;
    }

    public String getLocation() {
        return location;
    }
}
