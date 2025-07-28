package com.anpra.java_design_patterns.patterns.structural.decorator;

/**
 * 🏗️ Analogy: House Features Add-ons
 * Like adding optional features to a basic house (security system, smart home features, etc.)
 */
public interface House {
    String getDescription();
    double getCost();
}

// Basic implementation
public class BasicHouse implements House {
    @Override
    public String getDescription() {
        return "Basic House";
    }
    
    @Override
    public double getCost() {
        return 200000;
    }
}

// Base decorator
public abstract class HouseDecorator implements House {
    protected House house;
    
    public HouseDecorator(House house) {
        this.house = house;
    }
    
    @Override
    public String getDescription() {
        return house.getDescription();
    }
    
    @Override
    public double getCost() {
        return house.getCost();
    }
}

// Concrete decorators
public class SecuritySystem extends HouseDecorator {
    public SecuritySystem(House house) {
        super(house);
    }
    
    @Override
    public String getDescription() {
        return house.getDescription() + ", with Security System";
    }
    
    @Override
    public double getCost() {
        return house.getCost() + 5000;
    }
}

public class SmartHomeFeatures extends HouseDecorator {
    public SmartHomeFeatures(House house) {
        super(house);
    }
    
    @Override
    public String getDescription() {
        return house.getDescription() + ", with Smart Home Features";
    }
    
    @Override
    public double getCost() {
        return house.getCost() + 10000;
    }
}

public class SolarPanels extends HouseDecorator {
    public SolarPanels(House house) {
        super(house);
    }
    
    @Override
    public String getDescription() {
        return house.getDescription() + ", with Solar Panels";
    }
    
    @Override
    public double getCost() {
        return house.getCost() + 15000;
    }
}
