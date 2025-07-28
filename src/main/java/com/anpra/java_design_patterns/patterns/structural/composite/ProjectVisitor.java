package com.anpra.java_design_patterns.patterns.structural.composite;

/**
 * Visitor interface for project components
 */
public interface ProjectVisitor {
    void visitPhase(ProjectPhase phase);
    void visitTask(ConstructionTask task);
}
