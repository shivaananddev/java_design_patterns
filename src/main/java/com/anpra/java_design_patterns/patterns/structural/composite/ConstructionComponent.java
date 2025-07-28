package com.anpra.java_design_patterns.patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 🏗️ Analogy: Construction Cost Estimation
 * Like calculating total cost of a building including all components and subcomponents
 */
public interface ConstructionComponent {
    double calculateCost();
    void add(ConstructionComponent component);
    void remove(ConstructionComponent component);
    ConstructionComponent getChild(int index);
}

// Leaf class
public class BuildingMaterial implements ConstructionComponent {
    private final String name;
    private final double cost;
    
    public BuildingMaterial(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
    
    @Override
    public double calculateCost() {
        return cost;
    }
    
    @Override
    public void add(ConstructionComponent component) {
        throw new UnsupportedOperationException("Cannot add to a building material");
    }
    
    @Override
    public void remove(ConstructionComponent component) {
        throw new UnsupportedOperationException("Cannot remove from a building material");
    }
    
    @Override
    public ConstructionComponent getChild(int index) {
        throw new UnsupportedOperationException("Building material has no children");
    }
}

// Composite class
public class ConstructionPhase implements ConstructionComponent {
    private final String name;
    private final List<ConstructionComponent> components = new ArrayList<>();
    
    public ConstructionPhase(String name) {
        this.name = name;
    }
    
    @Override
    public double calculateCost() {
        return components.stream()
                .mapToDouble(ConstructionComponent::calculateCost)
                .sum();
    }
    
    @Override
    public void add(ConstructionComponent component) {
        components.add(component);
    }
    
    @Override
    public void remove(ConstructionComponent component) {
        components.remove(component);
    }
    
    @Override
    public ConstructionComponent getChild(int index) {
        return components.get(index);
    }
}

// Client code example
public class Building {
    private final ConstructionComponent foundation;
    private final ConstructionComponent structure;
    private final ConstructionComponent finishing;
    
    public Building() {
        foundation = new ConstructionPhase("Foundation");
        foundation.add(new BuildingMaterial("Concrete", 10000));
        foundation.add(new BuildingMaterial("Steel Reinforcement", 5000));
        
        structure = new ConstructionPhase("Structure");
        structure.add(new BuildingMaterial("Steel Frame", 20000));
        structure.add(new BuildingMaterial("Walls", 15000));
        
        finishing = new ConstructionPhase("Finishing");
        finishing.add(new BuildingMaterial("Paint", 3000));
        finishing.add(new BuildingMaterial("Flooring", 7000));
    }
    
    public double getTotalCost() {
        ConstructionPhase entireProject = new ConstructionPhase("Entire Project");
        entireProject.add(foundation);
        entireProject.add(structure);
        entireProject.add(finishing);
        return entireProject.calculateCost();
    }
}
