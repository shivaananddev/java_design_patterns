package com.anpra.java_design_patterns.patterns.structural.composite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Project analyzer visitor implementation
 */
public class ProjectAnalyzer implements ProjectVisitor {
    private final List<String> criticalPath;
    private BigDecimal totalCost;
    private int totalTasks;
    private int completedTasks;

    public ProjectAnalyzer() {
        this.criticalPath = new ArrayList<>();
        this.totalCost = BigDecimal.ZERO;
        this.totalTasks = 0;
        this.completedTasks = 0;
    }

    @Override
    public void visitPhase(ProjectPhase phase) {
        criticalPath.add(phase.getName());
        totalCost = totalCost.add(phase.getCost());
    }

    @Override
    public void visitTask(ConstructionTask task) {
        totalTasks++;
        if (task.isComplete()) {
            completedTasks++;
        }
        if (!task.getDependencies().isEmpty()) {
            criticalPath.add(task.getName());
        }
    }

    public List<String> getCriticalPath() {
        return new ArrayList<>(criticalPath);
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public double getCompletionPercentage() {
        return totalTasks == 0 ? 0 : 
            ((double) completedTasks / totalTasks) * 100;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    @Override
    public String toString() {
        return String.format("""
            Project Analysis:
            ----------------
            Total Tasks: %d
            Completed Tasks: %d
            Completion: %.2f%%
            Total Cost: $%s
            Critical Path: %s
            """,
            totalTasks, completedTasks, getCompletionPercentage(),
            totalCost, String.join(" -> ", criticalPath));
    }
}
