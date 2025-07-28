package com.anpra.java_design_patterns.patterns.structural.facade;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstructionFacadeTest {
    @Test
    void testCompleteConstructionProcess() {
        ConstructionFacade facade = new ConstructionFacade();
        assertDoesNotThrow(() -> facade.constructBuilding("Residential Building"));
    }
    
    @Test
    void testContractorServiceValidation() {
        ContractorService service = new ContractorService();
        
        Exception exception = assertThrows(IllegalStateException.class, 
            () -> service.startConstruction());
        assertEquals("Must hire contractors before starting construction", 
            exception.getMessage());
    }
    
    @Test
    void testInspectionProcess() {
        InspectionService service = new InspectionService();
        assertTrue(service.conductInspection());
    }
}
