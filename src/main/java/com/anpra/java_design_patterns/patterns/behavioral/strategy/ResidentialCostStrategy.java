package com.anpra.java_design_patterns.patterns.behavioral.strategy;

public class ResidentialCostStrategy implements CostCalculationStrategy {
    private static final double BASE_RATE = 150.0;
    private static final double RUSH_MULTIPLIER = 1.25;

    @Override
    public double calculateCost(double squareFootage, String location, boolean isRushOrder) {
        double baseCost = squareFootage * BASE_RATE;
        double locationMultiplier = getLocationMultiplier(location);
        double total = baseCost * locationMultiplier;
        
        return isRushOrder ? total * RUSH_MULTIPLIER : total;
    }

    private double getLocationMultiplier(String location) {
        return switch (location.toLowerCase()) {
            case "urban" -> 1.3;
            case "suburban" -> 1.1;
            case "rural" -> 1.0;
            default -> throw new IllegalArgumentException("Invalid location: " + location);
        };
    }
}
