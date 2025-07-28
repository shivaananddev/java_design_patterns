package com.anpra.java_design_patterns.patterns.structural.composite;

import java.math.BigDecimal;
import java.time.Duration;

/**
 * Leaf class representing a construction task
 */
public class ConstructionTask extends AbstractProjectComponent {
    private final Duration duration;
    private final BigDecimal cost;
    private final String description;

    public ConstructionTask(String name, Duration duration, BigDecimal cost, String description) {
        super(name);
        this.duration = duration;
        this.cost = cost;
        this.description = description;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void accept(ProjectVisitor visitor) {
        visitor.visitTask(this);
    }

    @Override
    public String toString() {
        return String.format("%sTask: %s - %s (Duration: %s, Cost: $%s)%n",
            getIndentation(), getName(), description, duration, cost);
    }
}
