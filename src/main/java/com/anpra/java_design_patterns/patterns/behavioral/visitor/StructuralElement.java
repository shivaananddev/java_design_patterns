package com.anpra.java_design_patterns.patterns.behavioral.visitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Structural element of a construction site
 */
public class StructuralElement implements SiteElement {
    private final String elementId;
    private final String location;
    private final String type;
    private final Map<String, Double> structuralMetrics;

    public StructuralElement(String elementId, String location, String type) {
        this.elementId = elementId;
        this.location = location;
        this.type = type;
        this.structuralMetrics = new HashMap<>();
    }

    public void addMetric(String name, double value) {
        structuralMetrics.put(name, value);
    }

    public double getMetric(String name) {
        return structuralMetrics.getOrDefault(name, 0.0);
    }

    public String getType() {
        return type;
    }

    public Map<String, Double> getAllMetrics() {
        return new HashMap<>(structuralMetrics);
    }

    @Override
    public void accept(SiteInspectionVisitor visitor) {
        visitor.visitStructuralElement(this);
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getElementId() {
        return elementId;
    }
}
