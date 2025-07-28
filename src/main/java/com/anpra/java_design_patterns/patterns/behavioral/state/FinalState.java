package com.anpra.java_design_patterns.patterns.behavioral.state;

/**
 * Final state implementation - represents completed project
 */
public class FinalState implements ProjectState {
    private final ConstructionProject project;

    public FinalState(ConstructionProject project) {
        this.project = project;
    }

    @Override
    public void planProject() {
        System.out.println("Project is already completed.");
    }

    @Override
    public void startConstruction() {
        System.out.println("Project is already completed.");
    }

    @Override
    public void inspect() {
        System.out.println("Project is already completed.");
    }

    @Override
    public void complete() {
        System.out.println("Project is already completed.");
    }

    @Override
    public String getStateName() {
        return "FINAL";
    }
}
