package com.anpra.java_design_patterns.patterns.structural.decorator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HouseTest {
    @Test
    void testBasicHouse() {
        House house = new BasicHouse();
        assertEquals("Basic House", house.getDescription());
        assertEquals(200000, house.getCost());
    }
    
    @Test
    void testHouseWithSecuritySystem() {
        House house = new SecuritySystem(new BasicHouse());
        assertEquals("Basic House, with Security System", house.getDescription());
        assertEquals(205000, house.getCost());
    }
    
    @Test
    void testFullyLoadedHouse() {
        House house = new SolarPanels(
                        new SmartHomeFeatures(
                            new SecuritySystem(
                                new BasicHouse())));
                                
        assertEquals("Basic House, with Security System, with Smart Home Features, with Solar Panels", 
                    house.getDescription());
        assertEquals(230000, house.getCost());
    }
    
    @Test
    void testMultipleDecorators() {
        House house = new BasicHouse();
        house = new SecuritySystem(house);
        house = new SmartHomeFeatures(house);
        
        assertEquals("Basic House, with Security System, with Smart Home Features", 
                    house.getDescription());
        assertEquals(215000, house.getCost());
    }
}
