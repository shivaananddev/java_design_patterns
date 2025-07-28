package com.anpra.java_design_patterns.patterns.behavioral.strategy;

/**
 * Strategy interface for construction cost calculation
 */
public interface CostCalculationStrategy {
    double calculateCost(double squareFootage, String location, boolean isRushOrder);
}
