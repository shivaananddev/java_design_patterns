package com.anpra.java_design_patterns.patterns.behavioral.state;

/**
 * Planning state implementation
 */
public class PlanningState implements ProjectState {
    private final ConstructionProject project;

    public PlanningState(ConstructionProject project) {
        this.project = project;
    }

    @Override
    public void planProject() {
        System.out.println("Project " + project.getProjectName() + " is already in planning phase.");
    }

    @Override
    public void startConstruction() {
        System.out.println("Moving from planning to construction phase.");
        project.setState(new ConstructionState(project));
    }

    @Override
    public void inspect() {
        System.out.println("Cannot inspect - project is still in planning phase.");
    }

    @Override
    public void complete() {
        System.out.println("Cannot complete - project hasn't started construction yet.");
    }

    @Override
    public String getStateName() {
        return "PLANNING";
    }
}
