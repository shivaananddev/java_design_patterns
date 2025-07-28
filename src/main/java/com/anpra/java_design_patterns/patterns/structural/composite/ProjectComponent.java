package com.anpra.java_design_patterns.patterns.structural.composite;

import java.util.List;
import java.math.BigDecimal;
import java.time.Duration;

/**
 * Component interface for project structure
 */
public interface ProjectComponent {
    String getName();
    Duration getDuration();
    BigDecimal getCost();
    void addDependency(ProjectComponent component);
    List<ProjectComponent> getDependencies();
    boolean isComplete();
    void markComplete();
    ProjectComponent getParent();
    void setParent(ProjectComponent parent);
    void accept(ProjectVisitor visitor);
}
