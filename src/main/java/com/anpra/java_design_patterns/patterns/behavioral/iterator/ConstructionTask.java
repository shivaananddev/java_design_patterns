package com.anpra.java_design_patterns.patterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Construction Task representing a unit of work in construction
 */
public class ConstructionTask {
    private final String taskId;
    private final String description;
    private final int duration;
    private final String dependency;

    public ConstructionTask(String taskId, String description, int duration, String dependency) {
        this.taskId = taskId;
        this.description = description;
        this.duration = duration;
        this.dependency = dependency;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public String getDependency() {
        return dependency;
    }

    @Override
    public String toString() {
        return String.format("Task[%s]: %s (Duration: %d days, Depends on: %s)",
            taskId, description, duration, dependency != null ? dependency : "none");
    }
}
