package com.anpra.java_design_patterns.patterns.behavioral.template;

import java.util.*;

/**
 * 🏗️ Analogy: Construction Project Workflow
 * Like a standard construction process that can be customized for different
 * types of buildings while maintaining the same basic steps
 */
public abstract class ConstructionTemplate {
    // Template method defining the construction algorithm
    public final void buildStructure() {
        prepareSite();
        layFoundation();
        constructFrame();
        addWalls();
        installUtilities();
        finishInterior();
        if (includeExteriorFinishing()) {
            finishExterior();
        }
        performInspection();
        documentConstruction();
    }

    // Common steps with default implementation
    protected void prepareSite() {
        System.out.println("Clearing site and setting up equipment");
    }

    protected void layFoundation() {
        System.out.println("Laying standard foundation");
    }

    // Abstract steps that must be implemented by subclasses
    protected abstract void constructFrame();
    protected abstract void addWalls();
    protected abstract void installUtilities();
    protected abstract void finishInterior();

    // Hook method - can be overridden by subclasses
    protected boolean includeExteriorFinishing() {
        return true;
    }

    protected void finishExterior() {
        System.out.println("Applying standard exterior finishing");
    }

    protected void performInspection() {
        System.out.println("Performing final inspection");
    }

    protected void documentConstruction() {
        System.out.println("Documenting construction process and creating final report");
    }
}

public class ResidentialConstruction extends ConstructionTemplate {
    private final boolean luxuryFinishes;

    public ResidentialConstruction(boolean luxuryFinishes) {
        this.luxuryFinishes = luxuryFinishes;
    }

    @Override
    protected void constructFrame() {
        System.out.println("Constructing wooden frame for residential building");
    }

    @Override
    protected void addWalls() {
        System.out.println("Adding insulated walls with drywall finish");
    }

    @Override
    protected void installUtilities() {
        System.out.println("Installing residential-grade electrical, plumbing, and HVAC");
    }

    @Override
    protected void finishInterior() {
        if (luxuryFinishes) {
            System.out.println("Applying luxury interior finishes");
        } else {
            System.out.println("Applying standard interior finishes");
        }
    }

    @Override
    protected void finishExterior() {
        System.out.println("Applying residential siding and roofing");
    }
}

public class CommercialConstruction extends ConstructionTemplate {
    private final int floors;
    private final boolean includeParking;

    public CommercialConstruction(int floors, boolean includeParking) {
        this.floors = floors;
        this.includeParking = includeParking;
    }

    @Override
    protected void layFoundation() {
        System.out.println("Laying reinforced foundation for " + floors + "-story building");
    }

    @Override
    protected void constructFrame() {
        System.out.println("Constructing steel frame for commercial building");
    }

    @Override
    protected void addWalls() {
        System.out.println("Adding commercial-grade walls with steel studs");
    }

    @Override
    protected void installUtilities() {
        System.out.println("Installing commercial-grade utilities");
        if (floors > 3) {
            System.out.println("Installing elevator systems");
        }
    }

    @Override
    protected void finishInterior() {
        System.out.println("Applying commercial interior finishes");
        if (includeParking) {
            System.out.println("Setting up parking facility");
        }
    }

    @Override
    protected void performInspection() {
        super.performInspection();
        System.out.println("Performing additional commercial code compliance checks");
    }
}

public class IndustrialConstruction extends ConstructionTemplate {
    private final String facilityType;
    private final boolean requiresSpecialVentilation;

    public IndustrialConstruction(String facilityType, boolean requiresSpecialVentilation) {
        this.facilityType = facilityType;
        this.requiresSpecialVentilation = requiresSpecialVentilation;
    }

    @Override
    protected void prepareSite() {
        super.prepareSite();
        System.out.println("Performing industrial site assessment and contamination check");
    }

    @Override
    protected void constructFrame() {
        System.out.println("Constructing heavy-duty industrial frame");
    }

    @Override
    protected void addWalls() {
        System.out.println("Adding industrial-grade walls with " + facilityType + " specifications");
    }

    @Override
    protected void installUtilities() {
        System.out.println("Installing industrial-grade utilities");
        if (requiresSpecialVentilation) {
            System.out.println("Installing specialized ventilation systems");
        }
    }

    @Override
    protected void finishInterior() {
        System.out.println("Applying industrial-grade finishes");
    }

    @Override
    protected boolean includeExteriorFinishing() {
        return false; // Industrial buildings often don't require decorative exterior
    }

    @Override
    protected void performInspection() {
        super.performInspection();
        System.out.println("Performing industrial safety and compliance inspection");
    }
}
