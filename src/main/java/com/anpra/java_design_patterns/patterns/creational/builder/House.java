package com.anpra.java_design_patterns.patterns.creational.builder;

/**
 * 🏗️ Analogy: House Construction
 * Building a house step by step with different configurations
 */
public class House {
    private final String foundation;
    private final String structure;
    private final String roof;
    private final String interior;
    private final boolean hasGarage;
    private final boolean hasSwimmingPool;
    private final boolean hasSolarPanels;

    private House(HouseBuilder builder) {
        this.foundation = builder.foundation;
        this.structure = builder.structure;
        this.roof = builder.roof;
        this.interior = builder.interior;
        this.hasGarage = builder.hasGarage;
        this.hasSwimmingPool = builder.hasSwimmingPool;
        this.hasSolarPanels = builder.hasSolarPanels;
    }

    public static class HouseBuilder {
        private String foundation;
        private String structure;
        private String roof;
        private String interior;
        private boolean hasGarage;
        private boolean hasSwimmingPool;
        private boolean hasSolarPanels;

        public HouseBuilder foundation(String foundation) {
            this.foundation = foundation;
            return this;
        }

        public HouseBuilder structure(String structure) {
            this.structure = structure;
            return this;
        }

        public HouseBuilder roof(String roof) {
            this.roof = roof;
            return this;
        }

        public HouseBuilder interior(String interior) {
            this.interior = interior;
            return this;
        }

        public HouseBuilder withGarage(boolean hasGarage) {
            this.hasGarage = hasGarage;
            return this;
        }

        public HouseBuilder withSwimmingPool(boolean hasSwimmingPool) {
            this.hasSwimmingPool = hasSwimmingPool;
            return this;
        }

        public HouseBuilder withSolarPanels(boolean hasSolarPanels) {
            this.hasSolarPanels = hasSolarPanels;
            return this;
        }

        public House build() {
            return new House(this);
        }
    }

    // Getters
    public String getFoundation() { return foundation; }
    public String getStructure() { return structure; }
    public String getRoof() { return roof; }
    public String getInterior() { return interior; }
    public boolean hasGarage() { return hasGarage; }
    public boolean hasSwimmingPool() { return hasSwimmingPool; }
    public boolean hasSolarPanels() { return hasSolarPanels; }
}
