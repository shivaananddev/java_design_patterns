package com.anpra.java_design_patterns.patterns.behavioral.command;

/**
 * Wall construction command implementation
 */
public class BuildWallCommand extends AbstractConstructionCommand {
    private final double length;
    private final double height;
    private final String material;
    private boolean isBuilt = false;

    public BuildWallCommand(String taskId, double length, double height, String material) {
        super(taskId, String.format("Build wall: %.1fm x %.1fm using %s", length, height, material));
        this.length = length;
        this.height = height;
        this.material = material;
    }

    @Override
    public void execute() {
        try {
            validateParameters();
            simulateWallConstruction();
            isBuilt = true;
            setLastResult(createSuccessResult("Wall constructed successfully"));
        } catch (Exception e) {
            setLastResult(createErrorResult(e));
            throw e;
        }
    }

    @Override
    public void undo() {
        if (!isBuilt) {
            setLastResult(createSuccessResult("Nothing to undo - wall not built"));
            return;
        }

        try {
            simulateWallDemolition();
            isBuilt = false;
            setLastResult(createSuccessResult("Wall demolished successfully"));
        } catch (Exception e) {
            setLastResult(createErrorResult(e));
            throw e;
        }
    }

    private void validateParameters() {
        if (length <= 0 || height <= 0) {
            throw new IllegalArgumentException("Length and height must be positive");
        }
        if (material == null || material.trim().isEmpty()) {
            throw new IllegalArgumentException("Material must be specified");
        }
    }

    private void simulateWallConstruction() {
        try {
            // Simulate time-consuming construction
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Wall construction interrupted", e);
        }
    }

    private void simulateWallDemolition() {
        try {
            // Simulate demolition
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Wall demolition interrupted", e);
        }
    }
}
