package com.anpra.java_design_patterns.patterns.behavioral.template;

/**
 * Concrete implementation for residential construction
 */
public class ResidentialConstruction extends ConstructionTemplate {
    private final boolean isLuxury;
    
    public ResidentialConstruction(boolean isLuxury) {
        this.isLuxury = isLuxury;
    }

    @Override
    protected void constructFrame() {
        System.out.println("Constructing residential frame with wood framing...");
    }

    @Override
    protected void installUtilities() {
        System.out.println("Installing residential utilities (electrical, plumbing, HVAC)...");
    }

    @Override
    protected void finishInterior() {
        System.out.println("Applying residential interior finishes...");
    }

    @Override
    protected boolean requiresSpecialFinishing() {
        return isLuxury;
    }

    @Override
    protected void applySpecialFinishing() {
        System.out.println("Applying luxury residential finishes (hardwood, marble, etc.)...");
    }
}
