package com.anpra.java_design_patterns.patterns.structural.decorator;

import java.math.BigDecimal;

/**
 * Concrete decorator that adds solar panels feature to a building
 */
public class SolarPanelsDecorator extends BuildingDecorator {
    private static final BigDecimal SOLAR_PANELS_COST = new BigDecimal("25000.00");
    private static final String FEATURE = "solar_panels";

    public SolarPanelsDecorator(Building building) {
        super(building);
        addFeature(FEATURE);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Solar Panels";
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(SOLAR_PANELS_COST);
    }
}
