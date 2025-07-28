package com.anpra.java_design_patterns.patterns.structural.decorator;

import java.math.BigDecimal;

/**
 * Concrete decorator that adds energy efficiency features like double-paned windows
 * and enhanced insulation to a building
 */
public class EnergyEfficientDecorator extends BuildingDecorator {
    private static final BigDecimal ENERGY_EFFICIENCY_COST = new BigDecimal("35000.00");
    private static final String FEATURE = "energy_efficient";

    public EnergyEfficientDecorator(Building building) {
        super(building);
        addFeature(FEATURE);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Energy Efficiency Package";
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(ENERGY_EFFICIENCY_COST);
    }
}
