package com.anpra.java_design_patterns.patterns.structural.decorator;

import java.math.BigDecimal;

/**
 * Concrete decorator that adds automated HVAC system with
 * zone control and smart temperature management
 */
public class AutomatedHVACDecorator extends BuildingDecorator {
    private static final BigDecimal AUTOMATED_HVAC_COST = new BigDecimal("30000.00");
    private static final String FEATURE = "automated_hvac";

    public AutomatedHVACDecorator(Building building) {
        super(building);
        addFeature(FEATURE);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Automated HVAC System";
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(AUTOMATED_HVAC_COST);
    }
}
