package com.anpra.java_design_patterns.patterns.structural.decorator;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class AdvancedBuildingDecoratorTest {

    @Test
    void shouldCombineMultipleAdvancedFeatures() {
        // Given
        Building basicBuilding = new BasicBuilding("Modern House", 3000, new BigDecimal("300000.00"));
        
        // When
        Building advancedBuilding = new AutomatedHVACDecorator(
            new SecuritySystemDecorator(
                new EnergyEfficientDecorator(basicBuilding)
            )
        );
        
        // Then
        assertEquals("Modern House with Energy Efficiency Package with Advanced Security System with Automated HVAC System", 
                    advancedBuilding.getDescription());
        assertEquals(new BigDecimal("385000.00"), advancedBuilding.getCost());
        assertTrue(advancedBuilding.hasFeature("energy_efficient"));
        assertTrue(advancedBuilding.hasFeature("security_system"));
        assertTrue(advancedBuilding.hasFeature("automated_hvac"));
    }

    @Test
    void shouldWorkWithAllPossibleFeatures() {
        // Given
        Building basicBuilding = new BasicBuilding("Luxury House", 4000, new BigDecimal("400000.00"));
        
        // When
        Building fullyFeaturedBuilding = new AutomatedHVACDecorator(
            new SecuritySystemDecorator(
                new EnergyEfficientDecorator(
                    new SmartHomeDecorator(
                        new SolarPanelsDecorator(basicBuilding)
                    )
                )
            )
        );
        
        // Then
        assertTrue(fullyFeaturedBuilding.hasFeature("solar_panels"));
        assertTrue(fullyFeaturedBuilding.hasFeature("smart_home"));
        assertTrue(fullyFeaturedBuilding.hasFeature("energy_efficient"));
        assertTrue(fullyFeaturedBuilding.hasFeature("security_system"));
        assertTrue(fullyFeaturedBuilding.hasFeature("automated_hvac"));
        assertEquals(new BigDecimal("525000.00"), fullyFeaturedBuilding.getCost());
    }

    @Test
    void shouldApplyEnergyEfficientFeatures() {
        // Given
        Building basicBuilding = new BasicBuilding("Eco House", 2500, new BigDecimal("250000.00"));
        
        // When
        Building energyEfficientBuilding = new EnergyEfficientDecorator(basicBuilding);
        
        // Then
        assertEquals("Eco House with Energy Efficiency Package", energyEfficientBuilding.getDescription());
        assertEquals(new BigDecimal("285000.00"), energyEfficientBuilding.getCost());
        assertTrue(energyEfficientBuilding.hasFeature("energy_efficient"));
    }
}
