package com.anpra.java_design_patterns.solid.isp.badcode;

/**
 * 🏗️ Analogy: House Construction (ISP Bad Example)
 * One interface forces all experts to implement all methods, even those irrelevant to their job.
 * This violates ISP, as experts must provide unnecessary implementations.
 */
interface HouseExpert {
    void paintHouse();
    void wireHouse();
    void plumbHouse();
}

class BadPainter implements HouseExpert {
    @Override
    public void paintHouse() {
        System.out.println("Painting the house...");
    }
    @Override
    public void wireHouse() {
        // Not needed
    }
    @Override
    public void plumbHouse() {
        // Not needed
    }
}

class BadElectrician implements HouseExpert {
    @Override
    public void paintHouse() {
        // Not needed
    }
    @Override
    public void wireHouse() {
        System.out.println("Wiring the house...");
    }
    @Override
    public void plumbHouse() {
        // Not needed
    }
}

class BadPlumber implements HouseExpert {
    @Override
    public void paintHouse() {
        // Not needed
    }
    @Override
    public void wireHouse() {
        // Not needed
    }
    @Override
    public void plumbHouse() {
        System.out.println("Plumbing the house...");
    }
}

class IspExample {
    public void buildHouse(HouseExpert expert) {
        expert.paintHouse();
        expert.wireHouse();
        expert.plumbHouse();
        System.out.println("House is ready!");
    }
}
