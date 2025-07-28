package com.anpra.java_design_patterns.patterns.structural.composite;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Composite class representing a project phase
 */
public class ProjectPhase extends AbstractProjectComponent {
    private final List<ProjectComponent> subComponents;

    public ProjectPhase(String name) {
        super(name);
        this.subComponents = new ArrayList<>();
    }

    public void addComponent(ProjectComponent component) {
        subComponents.add(component);
        component.setParent(this);
    }

    public void removeComponent(ProjectComponent component) {
        subComponents.remove(component);
        component.setParent(null);
    }

    public List<ProjectComponent> getComponents() {
        return new ArrayList<>(subComponents);
    }

    @Override
    public Duration getDuration() {
        return subComponents.stream()
            .map(ProjectComponent::getDuration)
            .reduce(Duration.ZERO, Duration::plus);
    }

    @Override
    public BigDecimal getCost() {
        return subComponents.stream()
            .map(ProjectComponent::getCost)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void markComplete() {
        if (!subComponents.stream().allMatch(ProjectComponent::isComplete)) {
            throw new IllegalStateException(
                "Cannot complete phase until all sub-components are complete");
        }
        super.markComplete();
    }

    @Override
    public void accept(ProjectVisitor visitor) {
        visitor.visitPhase(this);
        subComponents.forEach(component -> component.accept(visitor));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getIndentation())
              .append("Phase: ")
              .append(getName())
              .append(" (Duration: ")
              .append(getDuration())
              .append(", Cost: $")
              .append(getCost())
              .append(")\n");
        
        subComponents.forEach(component ->
            result.append(component.toString()));
        
        return result.toString();
    }
}
