package com.anpra.java_design_patterns.patterns.structural.composite;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstructionComponentTest {
    @Test
    void testBuildingMaterialCost() {
        ConstructionComponent material = new BuildingMaterial("Concrete", 1000);
        assertEquals(1000, material.calculateCost());
    }
    
    @Test
    void testConstructionPhaseCost() {
        ConstructionPhase foundation = new ConstructionPhase("Foundation");
        foundation.add(new BuildingMaterial("Concrete", 1000));
        foundation.add(new BuildingMaterial("Steel", 2000));
        
        assertEquals(3000, foundation.calculateCost());
    }
    
    @Test
    void testEntireBuildingCost() {
        Building building = new Building();
        assertTrue(building.getTotalCost() > 0);
    }
    
    @Test
    void testLeafOperations() {
        ConstructionComponent material = new BuildingMaterial("Test", 100);
        
        assertThrows(UnsupportedOperationException.class, 
            () -> material.add(new BuildingMaterial("New", 100)));
        assertThrows(UnsupportedOperationException.class, 
            () -> material.remove(new BuildingMaterial("New", 100)));
        assertThrows(UnsupportedOperationException.class, 
            () -> material.getChild(0));
    }
}
