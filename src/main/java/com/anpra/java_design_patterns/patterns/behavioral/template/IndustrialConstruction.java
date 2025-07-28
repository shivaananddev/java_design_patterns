package com.anpra.java_design_patterns.patterns.behavioral.template;

/**
 * Concrete implementation for industrial construction
 */
public class IndustrialConstruction extends ConstructionTemplate {
    private final String facilityType;
    private final boolean requiresHazardProtection;
    
    public IndustrialConstruction(String facilityType, boolean requiresHazardProtection) {
        this.facilityType = facilityType;
        this.requiresHazardProtection = requiresHazardProtection;
    }

    @Override
    protected void constructFrame() {
        System.out.println("Constructing industrial frame for " + facilityType + "...");
        System.out.println("Adding reinforced structural elements for heavy machinery...");
    }

    @Override
    protected void installUtilities() {
        System.out.println("Installing industrial-grade power systems...");
        System.out.println("Setting up specialized ventilation systems...");
        if (requiresHazardProtection) {
            System.out.println("Installing hazardous material handling systems...");
        }
    }

    @Override
    protected void finishInterior() {
        System.out.println("Applying industrial-grade flooring and wall finishes...");
    }

    @Override
    protected boolean requiresSpecialFinishing() {
        return requiresHazardProtection;
    }

    @Override
    protected void applySpecialFinishing() {
        System.out.println("Applying chemical-resistant coatings and safety features...");
    }
}
