package com.anpra.java_design_patterns.patterns.behavioral.state;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 🏗️ Analogy: Construction Project Phases
 * Like different phases of a construction project, each with its own
 * rules, requirements, and allowed operations
 */
public interface ConstructionState {
    void planProject(ConstructionProject project);
    void startConstruction(ConstructionProject project);
    void inspectWork(ConstructionProject project);
    void completePhase(ConstructionProject project);
    String getStateName();
}

public class ConstructionProject {
    private ConstructionState currentState;
    private final List<String> projectLog;
    private final Map<String, Boolean> completedTasks;
    private LocalDateTime lastStateChange;

    public ConstructionProject() {
        this.currentState = new PlanningState();
        this.projectLog = new ArrayList<>();
        this.completedTasks = new HashMap<>();
        this.lastStateChange = LocalDateTime.now();
    }

    public void setState(ConstructionState state) {
        this.currentState = state;
        this.lastStateChange = LocalDateTime.now();
        logStateChange(state.getStateName());
    }

    public void planProject() {
        currentState.planProject(this);
    }

    public void startConstruction() {
        currentState.startConstruction(this);
    }

    public void inspectWork() {
        currentState.inspectWork(this);
    }

    public void completePhase() {
        currentState.completePhase(this);
    }

    public void completeTask(String task) {
        completedTasks.put(task, true);
        logAction("Completed task: " + task);
    }

    public boolean isTaskCompleted(String task) {
        return completedTasks.getOrDefault(task, false);
    }

    private void logStateChange(String newState) {
        logAction("State changed to: " + newState);
    }

    private void logAction(String action) {
        projectLog.add(LocalDateTime.now() + ": " + action);
    }

    public List<String> getProjectLog() {
        return new ArrayList<>(projectLog);
    }

    public String getCurrentStateName() {
        return currentState.getStateName();
    }

    public LocalDateTime getLastStateChange() {
        return lastStateChange;
    }
}

public class PlanningState implements ConstructionState {
    @Override
    public void planProject(ConstructionProject project) {
        project.completeTask("Project Planning");
        project.setState(new PreConstructionState());
    }

    @Override
    public void startConstruction(ConstructionProject project) {
        throw new IllegalStateException("Cannot start construction in planning phase");
    }

    @Override
    public void inspectWork(ConstructionProject project) {
        throw new IllegalStateException("Cannot inspect work in planning phase");
    }

    @Override
    public void completePhase(ConstructionProject project) {
        if (!project.isTaskCompleted("Project Planning")) {
            throw new IllegalStateException("Planning tasks not completed");
        }
        project.setState(new PreConstructionState());
    }

    @Override
    public String getStateName() {
        return "Planning";
    }
}

public class PreConstructionState implements ConstructionState {
    @Override
    public void planProject(ConstructionProject project) {
        throw new IllegalStateException("Planning phase already completed");
    }

    @Override
    public void startConstruction(ConstructionProject project) {
        project.completeTask("Permits Obtained");
        project.completeTask("Materials Ordered");
        project.setState(new ConstructionState() {
            @Override
            public void planProject(ConstructionProject project) {
                throw new IllegalStateException("Cannot plan during construction");
            }

            @Override
            public void startConstruction(ConstructionProject project) {
                throw new IllegalStateException("Construction already started");
            }

            @Override
            public void inspectWork(ConstructionProject project) {
                project.completeTask("Inspection");
            }

            @Override
            public void completePhase(ConstructionProject project) {
                if (!project.isTaskCompleted("Inspection")) {
                    throw new IllegalStateException("Inspection required before completion");
                }
                project.setState(new CompletionState());
            }

            @Override
            public String getStateName() {
                return "Construction";
            }
        });
    }

    @Override
    public void inspectWork(ConstructionProject project) {
        throw new IllegalStateException("Cannot inspect work before construction starts");
    }

    @Override
    public void completePhase(ConstructionProject project) {
        throw new IllegalStateException("Cannot complete before construction starts");
    }

    @Override
    public String getStateName() {
        return "Pre-Construction";
    }
}

public class CompletionState implements ConstructionState {
    @Override
    public void planProject(ConstructionProject project) {
        throw new IllegalStateException("Project already completed");
    }

    @Override
    public void startConstruction(ConstructionProject project) {
        throw new IllegalStateException("Project already completed");
    }

    @Override
    public void inspectWork(ConstructionProject project) {
        project.completeTask("Final Inspection");
    }

    @Override
    public void completePhase(ConstructionProject project) {
        if (!project.isTaskCompleted("Final Inspection")) {
            throw new IllegalStateException("Final inspection required");
        }
        project.completeTask("Project Completion");
    }

    @Override
    public String getStateName() {
        return "Completion";
    }
}
