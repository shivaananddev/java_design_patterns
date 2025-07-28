package com.anpra.java_design_patterns.patterns.structural.bridge;

/**
 * Concrete implementor for excavator control
 */
public class ExcavatorController implements EquipmentController {
    private String mode = "IDLE";
    private boolean running = false;

    @Override
    public void start() {
        running = true;
        mode = "DIG";
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
        return String.format("Excavator status: %s, running: %b", mode, running);
    }
}
