package com.anpra.java_design_patterns.solid.isp.goodcode;

/**
 * 🏗️ Analogy: House Construction (ISP Good Example)
 * Each expert (Painter, Electrician, Plumber) only implements the interfaces relevant to their job.
 * No expert is forced to implement methods they don't need.
 */
interface Painting {
    void paintHouse();
}

interface Wiring {
    void wireHouse();
}

interface Plumbing {
    void plumbHouse();
}

class HousePainter implements Painting {
    @Override
    public void paintHouse() {
        System.out.println("Painting the house...");
    }
}

class HouseElectrician implements Wiring {
    @Override
    public void wireHouse() {
        System.out.println("Wiring the house...");
    }
}

class HousePlumber implements Plumbing {
    @Override
    public void plumbHouse() {
        System.out.println("Plumbing the house...");
    }
}

class IspExample {
    public void buildHouse(Painting painter, Wiring electrician, Plumbing plumber) {
        painter.paintHouse();
        electrician.wireHouse();
        plumber.plumbHouse();
        System.out.println("House is ready!");
    }
}
