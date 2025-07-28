package com.anpra.java_design_patterns.patterns.structural.bridge;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BridgePatternTest {
    @Test
    void testCraneOperation() {
        EquipmentController controller = new CraneController();
        ConstructionEquipment crane = new Crane("CRN-001", controller);
        
        crane.operate("LIFT");
        assertTrue(crane.getEquipmentStatus().contains("LIFT"));
        crane.shutdown();
        assertTrue(crane.getEquipmentStatus().contains("IDLE"));
    }

    @Test
    void testExcavatorOperation() {
        EquipmentController controller = new ExcavatorController();
        ConstructionEquipment excavator = new Excavator("EXC-001", controller);
        
        excavator.operate("DIG");
        assertTrue(excavator.getEquipmentStatus().contains("DIG"));
        excavator.shutdown();
        assertTrue(excavator.getEquipmentStatus().contains("IDLE"));
    }

    @Test
    void testMultipleModes() {
        EquipmentController controller = new CraneController();
        ConstructionEquipment crane = new Crane("CRN-002", controller);
        
        crane.operate("SWING");
        assertTrue(crane.getEquipmentStatus().contains("SWING"));
        crane.operate("LIFT");
        assertTrue(crane.getEquipmentStatus().contains("LIFT"));
    }
}
