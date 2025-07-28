package com.anpra.java_design_patterns.patterns.creational.prototype;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstructionBlueprintTest {
    @Test
    void testResidentialBlueprintCloning() {
        ResidentialBlueprint original = new ResidentialBlueprint();
        original.setProjectName("Villa");
        original.setSquareFootage(2500);
        original.setNumberOfBedrooms(4);
        original.setNumberOfBathrooms(3);
        original.addSpecification("High Ceilings");
        
        ResidentialBlueprint clone = (ResidentialBlueprint) original.clone();
        
        assertEquals(original.getProjectName(), clone.getProjectName());
        assertEquals(original.getSquareFootage(), clone.getSquareFootage());
        assertEquals(original.getNumberOfBedrooms(), clone.getNumberOfBedrooms());
        assertEquals(original.getNumberOfBathrooms(), clone.getNumberOfBathrooms());
        assertEquals(original.getSpecifications(), clone.getSpecifications());
        assertNotSame(original, clone);
    }

    @Test
    void testCommercialBlueprintCloning() {
        CommercialBlueprint original = new CommercialBlueprint();
        original.setProjectName("Office Complex");
        original.setSquareFootage(10000);
        original.setNumberOfFloors(5);
        original.setHasElevator(true);
        original.addSpecification("Glass Facade");
        
        CommercialBlueprint clone = (CommercialBlueprint) original.clone();
        
        assertEquals(original.getProjectName(), clone.getProjectName());
        assertEquals(original.getSquareFootage(), clone.getSquareFootage());
        assertEquals(original.getNumberOfFloors(), clone.getNumberOfFloors());
        assertEquals(original.hasElevator(), clone.hasElevator());
        assertEquals(original.getSpecifications(), clone.getSpecifications());
        assertNotSame(original, clone);
    }
}
