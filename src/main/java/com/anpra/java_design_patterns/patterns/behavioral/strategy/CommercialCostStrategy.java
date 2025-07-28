package com.anpra.java_design_patterns.patterns.behavioral.strategy;

public class CommercialCostStrategy implements CostCalculationStrategy {
    private static final double BASE_RATE = 200.0;
    private static final double RUSH_MULTIPLIER = 1.35;
    private static final double LARGE_PROJECT_THRESHOLD = 10000.0;
    private static final double LARGE_PROJECT_DISCOUNT = 0.85;

    @Override
    public double calculateCost(double squareFootage, String location, boolean isRushOrder) {
        double baseCost = squareFootage * BASE_RATE;
        double locationMultiplier = getLocationMultiplier(location);
        
        // Apply bulk discount for large projects
        if (squareFootage > LARGE_PROJECT_THRESHOLD) {
            baseCost *= LARGE_PROJECT_DISCOUNT;
        }
        
        double total = baseCost * locationMultiplier;
        return isRushOrder ? total * RUSH_MULTIPLIER : total;
    }

    private double getLocationMultiplier(String location) {
        return switch (location.toLowerCase()) {
            case "urban" -> 1.5;
            case "suburban" -> 1.2;
            case "rural" -> 1.0;
            default -> throw new IllegalArgumentException("Invalid location: " + location);
        };
    }
}
