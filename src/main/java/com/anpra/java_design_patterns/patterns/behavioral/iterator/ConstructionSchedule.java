package com.anpra.java_design_patterns.patterns.behavioral.iterator;

import java.util.*;

/**
 * Construction Schedule implementation using a custom iterator
 */
public class ConstructionSchedule implements Iterable<ConstructionTask> {
    private final List<ConstructionTask> tasks;
    private final Map<String, ConstructionTask> taskMap;

    public ConstructionSchedule() {
        this.tasks = new ArrayList<>();
        this.taskMap = new HashMap<>();
    }

    public void addTask(ConstructionTask task) {
        tasks.add(task);
        taskMap.put(task.getTaskId(), task);
    }

    public ConstructionTask getTask(String taskId) {
        return taskMap.get(taskId);
    }

    @Override
    public Iterator<ConstructionTask> iterator() {
        return new ScheduleIterator();
    }

    public ConstructionScheduleIterator scheduleIterator() {
        return new ScheduleIterator();
    }

    private class ScheduleIterator implements ConstructionScheduleIterator {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < tasks.size();
        }

        @Override
        public ConstructionTask next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return tasks.get(currentIndex++);
        }

        @Override
        public void reset() {
            currentIndex = 0;
        }

        @Override
        public boolean hasPreviousTask() {
            return currentIndex > 0;
        }

        @Override
        public ConstructionTask previousTask() {
            if (!hasPreviousTask()) {
                throw new NoSuchElementException();
            }
            return tasks.get(--currentIndex);
        }

        @Override
        public ConstructionTask peekNext() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return tasks.get(currentIndex);
        }
    }

    public int getTotalDuration() {
        return tasks.stream()
                   .mapToInt(ConstructionTask::getDuration)
                   .sum();
    }

    public List<ConstructionTask> getTasksByDependency(String dependencyId) {
        return tasks.stream()
                   .filter(task -> dependencyId.equals(task.getDependency()))
                   .toList();
    }
}
