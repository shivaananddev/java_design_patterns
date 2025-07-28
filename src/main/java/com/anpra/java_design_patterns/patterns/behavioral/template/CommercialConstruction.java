package com.anpra.java_design_patterns.patterns.behavioral.template;

/**
 * Concrete implementation for commercial construction
 */
public class CommercialConstruction extends ConstructionTemplate {
    private final int numberOfFloors;
    private final boolean isPremiumFinish;
    
    public CommercialConstruction(int numberOfFloors, boolean isPremiumFinish) {
        this.numberOfFloors = numberOfFloors;
        this.isPremiumFinish = isPremiumFinish;
    }

    @Override
    protected void constructFrame() {
        System.out.println("Constructing commercial frame with steel and concrete for " + numberOfFloors + " floors...");
    }

    @Override
    protected void installUtilities() {
        System.out.println("Installing commercial-grade utilities with backup systems...");
        System.out.println("Setting up commercial fire suppression systems...");
    }

    @Override
    protected void finishInterior() {
        System.out.println("Applying commercial interior finishes...");
    }

    @Override
    protected boolean requiresSpecialFinishing() {
        return isPremiumFinish;
    }

    @Override
    protected void applySpecialFinishing() {
        System.out.println("Applying premium commercial finishes (high-end facades, smart systems)...");
    }
}
