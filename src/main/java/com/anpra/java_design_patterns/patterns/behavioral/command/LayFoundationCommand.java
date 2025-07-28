package com.anpra.java_design_patterns.patterns.behavioral.command;

/**
 * Foundation laying command implementation
 */
public class LayFoundationCommand extends AbstractConstructionCommand {
    private final double area;
    private final String foundationType;
    private boolean isComplete = false;

    public LayFoundationCommand(String taskId, double area, String foundationType) {
        super(taskId, "Lay foundation for area: " + area + "m², type: " + foundationType);
        this.area = area;
        this.foundationType = foundationType;
    }

    @Override
    public void execute() {
        try {
            validateParameters();
            simulateFoundationWork();
            isComplete = true;
            setLastResult(createSuccessResult("Foundation laid successfully"));
        } catch (Exception e) {
            setLastResult(createErrorResult(e));
            throw e;
        }
    }

    @Override
    public void undo() {
        if (!isComplete) {
            setLastResult(createSuccessResult("Nothing to undo - foundation not laid"));
            return;
        }
        
        try {
            simulateFoundationRemoval();
            isComplete = false;
            setLastResult(createSuccessResult("Foundation removal completed"));
        } catch (Exception e) {
            setLastResult(createErrorResult(e));
            throw e;
        }
    }

    private void validateParameters() {
        if (area <= 0) {
            throw new IllegalArgumentException("Area must be positive");
        }
        if (foundationType == null || foundationType.trim().isEmpty()) {
            throw new IllegalArgumentException("Foundation type must be specified");
        }
    }

    private void simulateFoundationWork() {
        // Simulate time-consuming work
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Foundation work interrupted", e);
        }
    }

    private void simulateFoundationRemoval() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Foundation removal interrupted", e);
        }
    }
}
