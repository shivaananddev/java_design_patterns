package com.anpra.java_design_patterns.patterns.creational.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HouseTest {
    @Test
    void testBasicHouseConstruction() {
        House house = new House.HouseBuilder()
            .foundation("Concrete")
            .structure("Brick")
            .roof("Shingle")
            .interior("Modern")
            .build();
        
        assertEquals("Concrete", house.getFoundation());
        assertEquals("Brick", house.getStructure());
        assertEquals("Shingle", house.getRoof());
        assertEquals("Modern", house.getInterior());
        assertFalse(house.hasGarage());
        assertFalse(house.hasSwimmingPool());
        assertFalse(house.hasSolarPanels());
    }

    @Test
    void testLuxuryHouseConstruction() {
        House house = new House.HouseBuilder()
            .foundation("Reinforced Concrete")
            .structure("Steel Frame")
            .roof("Slate")
            .interior("Luxury")
            .withGarage(true)
            .withSwimmingPool(true)
            .withSolarPanels(true)
            .build();
        
        assertTrue(house.hasGarage());
        assertTrue(house.hasSwimmingPool());
        assertTrue(house.hasSolarPanels());
    }
}
