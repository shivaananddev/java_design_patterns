package com.anpra.java_design_patterns.solid.srp.goodcode;

/**
 * 🏗️ Analogy: House Construction (SRP Good Example)
 * In a house, plumbing, wiring, and framing are separate systems, each maintained by different experts.
 * In software, each class should have a single responsibility, just like each expert handles only their system.
 */
public class PlumbingService {
    public void installPlumbing() {
        System.out.println("Installing plumbing...");
    }
}

public class WiringService {
    public void installWiring() {
        System.out.println("Installing wiring...");
    }
}

public class FramingService {
    public void buildFrame() {
        System.out.println("Building house frame...");
    }
}

/**
 * ConstructionManager coordinates the experts, but does not do their jobs.
 */
public class ConstructionManager {
    private final PlumbingService plumbingService;
    private final WiringService wiringService;
    private final FramingService framingService;

    public ConstructionManager(PlumbingService plumbingService, WiringService wiringService, FramingService framingService) {
        this.plumbingService = plumbingService;
        this.wiringService = wiringService;
        this.framingService = framingService;
    }

    public void constructHouse() {
        framingService.buildFrame();
        plumbingService.installPlumbing();
        wiringService.installWiring();
        System.out.println("House construction complete!");
    }
}
