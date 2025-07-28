package com.anpra.java_design_patterns.solid.ocp.badcode;

/**
 * 🏗️ Analogy: House Construction (OCP Bad Example)
 * If you want to add a new type of house component, you have to modify the HouseBuilder class directly.
 * This violates OCP, as changes require modifying existing code, risking bugs and breaking functionality.
 */
public class HouseBuilder {
    public void build(String componentType) {
        if (componentType.equals("plumbing")) {
            System.out.println("Installing plumbing...");
        } else if (componentType.equals("electrical")) {
            System.out.println("Installing electrical wiring...");
        } else if (componentType.equals("solar")) {
            System.out.println("Installing solar panels...");
        } else {
            System.out.println("Unknown component type!");
        }
    }
}
