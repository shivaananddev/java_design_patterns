package com.anpra.java_design_patterns.patterns.behavioral.state;

/**
 * Inspection state implementation
 */
public class InspectionState implements ProjectState {
    private final ConstructionProject project;
    private int inspectionCount = 0;
    private static final int REQUIRED_INSPECTIONS = 3;

    public InspectionState(ConstructionProject project) {
        this.project = project;
    }

    @Override
    public void planProject() {
        System.out.println("Cannot plan - project is in inspection phase.");
    }

    @Override
    public void startConstruction() {
        System.out.println("Cannot start construction - inspections are in progress.");
    }

    @Override
    public void inspect() {
        inspectionCount++;
        System.out.println("Conducting inspection " + inspectionCount + " of " + REQUIRED_INSPECTIONS);
        
        if (inspectionCount >= REQUIRED_INSPECTIONS) {
            System.out.println("All required inspections completed.");
            project.setState(new CompletionState(project));
        }
    }

    @Override
    public void complete() {
        System.out.println("Cannot complete - " + 
            (REQUIRED_INSPECTIONS - inspectionCount) + " inspections remaining.");
    }

    @Override
    public String getStateName() {
        return "INSPECTION";
    }
}
