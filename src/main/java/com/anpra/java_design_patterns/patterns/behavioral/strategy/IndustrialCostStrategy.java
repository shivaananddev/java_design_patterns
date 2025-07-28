package com.anpra.java_design_patterns.patterns.behavioral.strategy;

public class IndustrialCostStrategy implements CostCalculationStrategy {
    private static final double BASE_RATE = 250.0;
    private static final double RUSH_MULTIPLIER = 1.45;
    private static final double HAZMAT_MULTIPLIER = 1.75;

    @Override
    public double calculateCost(double squareFootage, String location, boolean isRushOrder) {
        double baseCost = squareFootage * BASE_RATE;
        double locationMultiplier = getLocationMultiplier(location);
        
        // Additional cost for industrial hazmat requirements
        if (location.toLowerCase().contains("hazmat")) {
            baseCost *= HAZMAT_MULTIPLIER;
        }
        
        double total = baseCost * locationMultiplier;
        return isRushOrder ? total * RUSH_MULTIPLIER : total;
    }

    private double getLocationMultiplier(String location) {
        return switch (location.toLowerCase()) {
            case "urban", "urban-hazmat" -> 1.6;
            case "suburban", "suburban-hazmat" -> 1.3;
            case "rural", "rural-hazmat" -> 1.1;
            default -> throw new IllegalArgumentException("Invalid location: " + location);
        };
    }
}
