package com.anpra.java_design_patterns.patterns.structural.decorator;

import java.math.BigDecimal;

/**
 * Concrete decorator that adds advanced security features like
 * cameras, motion sensors, and biometric access
 */
public class SecuritySystemDecorator extends BuildingDecorator {
    private static final BigDecimal SECURITY_SYSTEM_COST = new BigDecimal("20000.00");
    private static final String FEATURE = "security_system";

    public SecuritySystemDecorator(Building building) {
        super(building);
        addFeature(FEATURE);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with Advanced Security System";
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(SECURITY_SYSTEM_COST);
    }
}
