package com.anpra.java_design_patterns.patterns.behavioral.template;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstructionTemplateTest {
    
    @Test
    void testResidentialConstruction() {
        ConstructionTemplate residential = new ResidentialConstruction(true);
        assertDoesNotThrow(() -> residential.buildStructure());
    }

    @Test
    void testCommercialConstruction() {
        ConstructionTemplate commercial = new CommercialConstruction(5, true);
        assertDoesNotThrow(() -> commercial.buildStructure());
    }

    @Test
    void testIndustrialConstruction() {
        ConstructionTemplate industrial = new IndustrialConstruction("Manufacturing", true);
        assertDoesNotThrow(() -> industrial.buildStructure());
    }

    @Test
    void testResidentialWithDifferentFinishes() {
        ResidentialConstruction luxuryHome = new ResidentialConstruction(true);
        ResidentialConstruction standardHome = new ResidentialConstruction(false);
        
        // Both should complete without exceptions
        assertDoesNotThrow(() -> {
            luxuryHome.buildStructure();
            standardHome.buildStructure();
        });
    }

    @Test
    void testCommercialWithDifferentConfigurations() {
        CommercialConstruction highRise = new CommercialConstruction(10, true);
        CommercialConstruction smallOffice = new CommercialConstruction(2, false);
        
        assertDoesNotThrow(() -> {
            highRise.buildStructure();
            smallOffice.buildStructure();
        });
    }

    @Test
    void testIndustrialWithDifferentRequirements() {
        IndustrialConstruction chemicalPlant = new IndustrialConstruction("Chemical Plant", true);
        IndustrialConstruction warehouse = new IndustrialConstruction("Warehouse", false);
        
        assertDoesNotThrow(() -> {
            chemicalPlant.buildStructure();
            warehouse.buildStructure();
        });
    }
}
