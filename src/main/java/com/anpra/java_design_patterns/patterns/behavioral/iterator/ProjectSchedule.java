package com.anpra.java_design_patterns.patterns.behavioral.iterator;

import java.util.NoSuchElementException;

/**
 * 🏗️ Analogy: Construction Project Task Schedule
 * Like iterating through construction tasks in different ways (by phase, by priority, etc.)
 */
public class ConstructionTask {
    private final String name;
    private final int priority;
    private final String phase;
    private final int duration;

    public ConstructionTask(String name, int priority, String phase, int duration) {
        this.name = name;
        this.priority = priority;
        this.phase = phase;
        this.duration = duration;
    }

    // Getters
    public String getName() { return name; }
    public int getPriority() { return priority; }
    public String getPhase() { return phase; }
    public int getDuration() { return duration; }
}

public interface TaskIterator {
    boolean hasNext();
    ConstructionTask next();
    void reset();
}

public class ProjectSchedule {
    private final ConstructionTask[] tasks;
    private int size;
    private static final int DEFAULT_CAPACITY = 100;

    public ProjectSchedule() {
        this.tasks = new ConstructionTask[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void addTask(ConstructionTask task) {
        if (size < tasks.length) {
            tasks[size++] = task;
        }
    }

    public TaskIterator getPhaseIterator(String phase) {
        return new PhaseTaskIterator(tasks, size, phase);
    }

    public TaskIterator getPriorityIterator() {
        return new PriorityTaskIterator(tasks, size);
    }

    public TaskIterator getScheduleIterator() {
        return new ScheduleTaskIterator(tasks, size);
    }
}

public class PhaseTaskIterator implements TaskIterator {
    private final ConstructionTask[] tasks;
    private final int size;
    private final String phase;
    private int currentPosition;

    public PhaseTaskIterator(ConstructionTask[] tasks, int size, String phase) {
        this.tasks = tasks;
        this.size = size;
        this.phase = phase;
        this.currentPosition = 0;
    }

    @Override
    public boolean hasNext() {
        while (currentPosition < size) {
            if (tasks[currentPosition] != null && 
                phase.equals(tasks[currentPosition].getPhase())) {
                return true;
            }
            currentPosition++;
        }
        return false;
    }

    @Override
    public ConstructionTask next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return tasks[currentPosition++];
    }

    @Override
    public void reset() {
        currentPosition = 0;
    }
}

public class PriorityTaskIterator implements TaskIterator {
    private final ConstructionTask[] tasks;
    private final int size;
    private int currentPriority;
    private int currentPosition;

    public PriorityTaskIterator(ConstructionTask[] tasks, int size) {
        this.tasks = tasks;
        this.size = size;
        this.currentPriority = 1;
        this.currentPosition = 0;
    }

    @Override
    public boolean hasNext() {
        while (currentPriority <= 5) {  // Assuming priority 1-5
            while (currentPosition < size) {
                if (tasks[currentPosition] != null && 
                    tasks[currentPosition].getPriority() == currentPriority) {
                    return true;
                }
                currentPosition++;
            }
            currentPriority++;
            currentPosition = 0;
        }
        return false;
    }

    @Override
    public ConstructionTask next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return tasks[currentPosition++];
    }

    @Override
    public void reset() {
        currentPriority = 1;
        currentPosition = 0;
    }
}

public class ScheduleTaskIterator implements TaskIterator {
    private final ConstructionTask[] tasks;
    private final int size;
    private int currentPosition;

    public ScheduleTaskIterator(ConstructionTask[] tasks, int size) {
        this.tasks = tasks;
        this.size = size;
        this.currentPosition = 0;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < size && tasks[currentPosition] != null;
    }

    @Override
    public ConstructionTask next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return tasks[currentPosition++];
    }

    @Override
    public void reset() {
        currentPosition = 0;
    }
}
