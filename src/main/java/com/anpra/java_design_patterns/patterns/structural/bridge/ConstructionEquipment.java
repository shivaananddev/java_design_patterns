package com.anpra.java_design_patterns.patterns.structural.bridge;

/**
 * Abstraction for construction equipment (Bridge)
 */
public abstract class ConstructionEquipment {
    protected EquipmentController controller;
    protected final String equipmentId;

    protected ConstructionEquipment(String equipmentId, EquipmentController controller) {
        this.equipmentId = equipmentId;
        this.controller = controller;
    }

    public abstract void operate(String mode);
    public abstract void shutdown();
    public String getEquipmentStatus() {
        return controller.getStatus();
    }
    public String getEquipmentId() {
        return equipmentId;
    }
}
