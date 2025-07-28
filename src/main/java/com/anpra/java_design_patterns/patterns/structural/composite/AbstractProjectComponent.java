package com.anpra.java_design_patterns.patterns.structural.composite;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for project components
 */
public abstract class AbstractProjectComponent implements ProjectComponent {
    protected final String name;
    protected final List<ProjectComponent> dependencies;
    protected boolean completed;
    protected ProjectComponent parent;

    protected AbstractProjectComponent(String name) {
        this.name = name;
        this.dependencies = new ArrayList<>();
        this.completed = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addDependency(ProjectComponent component) {
        if (!dependencies.contains(component)) {
            dependencies.add(component);
        }
    }

    @Override
    public List<ProjectComponent> getDependencies() {
        return Collections.unmodifiableList(dependencies);
    }

    @Override
    public boolean isComplete() {
        return completed;
    }

    @Override
    public void markComplete() {
        if (!dependencies.stream().allMatch(ProjectComponent::isComplete)) {
            throw new IllegalStateException("Cannot complete component until all dependencies are complete");
        }
        this.completed = true;
    }

    @Override
    public ProjectComponent getParent() {
        return parent;
    }

    @Override
    public void setParent(ProjectComponent parent) {
        this.parent = parent;
    }

    protected String getIndentation() {
        StringBuilder indent = new StringBuilder();
        ProjectComponent current = this;
        while (current.getParent() != null) {
            indent.append("  ");
            current = current.getParent();
        }
        return indent.toString();
    }
}
