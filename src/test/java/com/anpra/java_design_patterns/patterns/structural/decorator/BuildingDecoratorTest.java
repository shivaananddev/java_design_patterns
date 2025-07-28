package com.anpra.java_design_patterns.patterns.structural.decorator;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BuildingDecoratorTest {
    
    @Test
    void shouldAddFeaturesAndCalculateCostCorrectly() {
        // Given
        Building basicBuilding = new BasicBuilding("Simple House", 2000, new BigDecimal("200000.00"));
        
        // When
        Building smartHome = new SmartHomeDecorator(basicBuilding);
        Building smartHomeWithSolar = new SolarPanelsDecorator(smartHome);
        
        // Then
        assertEquals("Simple House with Smart Home Features with Solar Panels", 
                    smartHomeWithSolar.getDescription());
        assertEquals(new BigDecimal("240000.00"), smartHomeWithSolar.getCost());
        assertEquals(2000, smartHomeWithSolar.getSquareFootage());
        assertTrue(smartHomeWithSolar.hasFeature("smart_home"));
        assertTrue(smartHomeWithSolar.hasFeature("solar_panels"));
    }

    @Test
    void shouldStackDecoratorsInDifferentOrder() {
        // Given
        Building basicBuilding = new BasicBuilding("Simple House", 2000, new BigDecimal("200000.00"));
        
        // When
        Building withSolar = new SolarPanelsDecorator(basicBuilding);
        Building withSolarAndSmart = new SmartHomeDecorator(withSolar);
        
        // Then
        assertEquals("Simple House with Solar Panels with Smart Home Features", 
                    withSolarAndSmart.getDescription());
        assertEquals(new BigDecimal("240000.00"), withSolarAndSmart.getCost());
        assertTrue(withSolarAndSmart.hasFeature("smart_home"));
        assertTrue(withSolarAndSmart.hasFeature("solar_panels"));
    }

    @Test
    void shouldMaintainBasicBuildingFunctionality() {
        // Given
        Building basicBuilding = new BasicBuilding("Simple House", 2000, new BigDecimal("200000.00"));
        
        // Then
        assertEquals("Simple House", basicBuilding.getDescription());
        assertEquals(new BigDecimal("200000.00"), basicBuilding.getCost());
        assertEquals(2000, basicBuilding.getSquareFootage());
        assertFalse(basicBuilding.hasFeature("smart_home"));
        assertFalse(basicBuilding.hasFeature("solar_panels"));
    }
}
