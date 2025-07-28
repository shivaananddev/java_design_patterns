package com.anpra.java_design_patterns.solid.srp.badcode;

/**
 * 🏗️ Analogy: House Construction (SRP Bad Example)
 * In this example, one class (HandymanService) tries to handle plumbing, wiring, and framing all by itself.
 * This violates SRP, as changes to one responsibility may affect others and make maintenance difficult.
 */
public class HandymanService {
    public void installPlumbing() {
        System.out.println("Installing plumbing...");
    }
    public void installWiring() {
        System.out.println("Installing wiring...");
    }
    public void buildFrame() {
        System.out.println("Building house frame...");
    }
    public void constructHouse() {
        buildFrame();
        installPlumbing();
        installWiring();
        System.out.println("House construction complete!");
    }
}
