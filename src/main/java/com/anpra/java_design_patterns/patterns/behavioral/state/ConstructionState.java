package com.anpra.java_design_patterns.patterns.behavioral.state;

/**
 * Construction state implementation
 */
public class ConstructionState implements ProjectState {
    private final ConstructionProject project;
    private boolean readyForInspection = false;

    public ConstructionState(ConstructionProject project) {
        this.project = project;
    }

    @Override
    public void planProject() {
        System.out.println("Cannot plan - construction is already in progress.");
    }

    @Override
    public void startConstruction() {
        System.out.println("Construction is already in progress for " + project.getProjectName());
    }

    @Override
    public void inspect() {
        if (!readyForInspection) {
            System.out.println("Preparing for inspection...");
            readyForInspection = true;
        } else {
            System.out.println("Moving to inspection phase.");
            project.setState(new InspectionState(project));
        }
    }

    @Override
    public void complete() {
        System.out.println("Cannot complete - project needs inspection first.");
    }

    @Override
    public String getStateName() {
        return "CONSTRUCTION";
    }
}
