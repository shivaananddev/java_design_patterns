package com.anpra.java_design_patterns.patterns.structural.bridge;

/**
 * Refined abstraction for crane equipment
 */
public class Crane extends ConstructionEquipment {
    public Crane(String equipmentId, EquipmentController controller) {
        super(equipmentId, controller);
    }

    @Override
    public void operate(String mode) {
        controller.setMode(mode);
        controller.start();
    }

    @Override
    public void shutdown() {
        controller.stop();
    }
}
