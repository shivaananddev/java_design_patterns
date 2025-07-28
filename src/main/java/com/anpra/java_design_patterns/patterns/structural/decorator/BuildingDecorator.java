package com.anpra.java_design_patterns.patterns.structural.decorator;

import java.math.BigDecimal;

/**
 * Abstract decorator class that implements Building interface
 * and holds a reference to a decorated Building object
 */
public abstract class BuildingDecorator implements Building {
    protected final Building decoratedBuilding;

    public BuildingDecorator(Building building) {
        this.decoratedBuilding = building;
    }

    @Override
    public String getDescription() {
        return decoratedBuilding.getDescription();
    }

    @Override
    public BigDecimal getCost() {
        return decoratedBuilding.getCost();
    }

    @Override
    public int getSquareFootage() {
        return decoratedBuilding.getSquareFootage();
    }

    @Override
    public void addFeature(String feature) {
        decoratedBuilding.addFeature(feature);
    }

    @Override
    public boolean hasFeature(String feature) {
        return decoratedBuilding.hasFeature(feature);
    }
}
