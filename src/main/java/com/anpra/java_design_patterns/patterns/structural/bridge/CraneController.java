package com.anpra.java_design_patterns.patterns.structural.bridge;

/**
 * Concrete implementor for crane control
 */
public class CraneController implements EquipmentController {
    private String mode = "IDLE";
    private boolean running = false;

    @Override
    public void start() {
        running = true;
        mode = "LIFT";
    }

    @Override
    public void stop() {
        running = false;
        mode = "IDLE";
    }

    @Override
    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String getStatus() {
        return String.format("Crane status: %s, running: %b", mode, running);
    }
}
