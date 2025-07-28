package com.anpra.java_design_patterns.patterns.creational.factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstructionEquipmentFactoryTest {
    @Test
    void testCreateCrane() {
        ConstructionEquipment crane = ConstructionEquipmentFactory.createEquipment("crane", "HC100");
        
        assertNotNull(crane);
        assertEquals("Crane", crane.getEquipmentType());
        assertTrue(crane instanceof Crane);
    }

    @Test
    void testCreateBulldozer() {
        ConstructionEquipment bulldozer = ConstructionEquipmentFactory.createEquipment("bulldozer", "BD200");
        
        assertNotNull(bulldozer);
        assertEquals("Bulldozer", bulldozer.getEquipmentType());
        assertTrue(bulldozer instanceof Bulldozer);
    }

    @Test
    void testInvalidEquipmentType() {
        assertThrows(IllegalArgumentException.class, () -> {
            ConstructionEquipmentFactory.createEquipment("invalid", "model");
        });
    }
}
