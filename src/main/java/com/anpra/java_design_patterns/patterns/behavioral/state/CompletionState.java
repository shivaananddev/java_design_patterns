package com.anpra.java_design_patterns.patterns.behavioral.state;

/**
 * Completion state implementation
 */
public class CompletionState implements ProjectState {
    private final ConstructionProject project;
    private boolean documentationComplete = false;

    public CompletionState(ConstructionProject project) {
        this.project = project;
    }

    @Override
    public void planProject() {
        System.out.println("Cannot plan - project is in completion phase.");
    }

    @Override
    public void startConstruction() {
        System.out.println("Cannot start construction - project is completing.");
    }

    @Override
    public void inspect() {
        System.out.println("All inspections have been completed.");
    }

    @Override
    public void complete() {
        if (!documentationComplete) {
            System.out.println("Preparing final documentation...");
            documentationComplete = true;
        } else {
            System.out.println("Project " + project.getProjectName() + " has been completed successfully!");
            project.setState(new FinalState(project));
        }
    }

    @Override
    public String getStateName() {
        return "COMPLETION";
    }
}
