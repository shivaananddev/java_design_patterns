package com.anpra.java_design_patterns.patterns.structural.decorator;

import java.math.BigDecimal;

/**
 * Concrete decorator that adds smart home features to a building
 */
public class SmartHomeDecorator extends BuildingDecorator {
    private static final BigDecimal SMART_HOME_COST = new BigDecimal("15000.00");
    private static final String FEATURE = "smart_home";

    public SmartHomeDecorator(Building building) {
        super(building);
        addFeature(FEATURE);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Smart Home Features";
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(SMART_HOME_COST);
    }
}
