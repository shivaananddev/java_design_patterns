package com.anpra.java_design_patterns.patterns.structural.bridge;

/**
 * Equipment control interface (Implementor)
 */
public interface EquipmentController {
    void start();
    void stop();
    void setMode(String mode);
    String getStatus();
}
