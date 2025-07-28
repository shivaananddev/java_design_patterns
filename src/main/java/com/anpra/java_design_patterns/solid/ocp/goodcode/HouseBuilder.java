package com.anpra.java_design_patterns.solid.ocp.goodcode;

/**
 * 🏗️ Analogy: House Construction (OCP Good Example)
 * If you want to add a new type of house component (e.g., SolarPanel), you should be able to do so without modifying existing code.
 * The system is open for extension, but closed for modification.
 */
public interface HouseComponent {
    void install();
}

public class PlumbingComponent implements HouseComponent {
    @Override
    public void install() {
        System.out.println("Installing plumbing...");
    }
}

public class ElectricalComponent implements HouseComponent {
    @Override
    public void install() {
        System.out.println("Installing electrical wiring...");
    }
}

public class SolarPanelComponent implements HouseComponent {
    @Override
    public void install() {
        System.out.println("Installing solar panels...");
    }
}

public class HouseBuilder {
    public void build(HouseComponent component) {
        component.install();
    }
}
