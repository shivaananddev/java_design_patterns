package com.anpra.java_design_patterns.patterns.structural.decorator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Concrete component implementing basic building
 */
public class BasicBuilding implements Building {
    private final String description;
    private final int squareFootage;
    private final BigDecimal baseCost;
    private final Set<String> features;

    public BasicBuilding(String description, int squareFootage, BigDecimal baseCost) {
        this.description = description;
        this.squareFootage = squareFootage;
        this.baseCost = baseCost;
        this.features = new HashSet<>();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public BigDecimal getCost() {
        return baseCost;
    }

    @Override
    public int getSquareFootage() {
        return squareFootage;
    }

    @Override
    public void addFeature(String feature) {
        features.add(feature);
    }

    @Override
    public boolean hasFeature(String feature) {
        return features.contains(feature);
    }
}
