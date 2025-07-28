package com.anpra.java_design_patterns.patterns.behavioral.state;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjectStateTest {
    
    @Test
    void testProjectStateTransitions() {
        ConstructionProject project = new ConstructionProject("Test Project");
        
        // Initial state should be Planning
        assertEquals("PLANNING", project.getCurrentStateName());
        
        // Test invalid operations in Planning state
        project.inspect();
        assertEquals("PLANNING", project.getCurrentStateName());
        
        project.complete();
        assertEquals("PLANNING", project.getCurrentStateName());
        
        // Move to Construction state
        project.startConstruction();
        assertEquals("CONSTRUCTION", project.getCurrentStateName());
        
        // Test Construction state behavior
        project.planProject(); // Should not change state
        assertEquals("CONSTRUCTION", project.getCurrentStateName());
        
        // Move to Inspection state
        project.inspect(); // First call prepares for inspection
        assertEquals("CONSTRUCTION", project.getCurrentStateName());
        project.inspect(); // Second call moves to inspection
        assertEquals("INSPECTION", project.getCurrentStateName());
        
        // Complete required inspections
        project.inspect(); // 1
        project.inspect(); // 2
        project.inspect(); // 3 - should move to completion
        assertEquals("COMPLETION", project.getCurrentStateName());
        
        // Complete the project
        project.complete(); // First call prepares documentation
        assertEquals("COMPLETION", project.getCurrentStateName());
        project.complete(); // Second call completes the project
        assertEquals("FINAL", project.getCurrentStateName());
        
        // Test final state
        project.planProject();
        project.startConstruction();
        project.inspect();
        project.complete();
        assertEquals("FINAL", project.getCurrentStateName()); // Should remain in final state
    }
    
    @Test
    void testInvalidStateTransitions() {
        ConstructionProject project = new ConstructionProject("Invalid Test");
        
        // Try to complete from planning state
        project.complete();
        assertEquals("PLANNING", project.getCurrentStateName());
        
        // Move to construction
        project.startConstruction();
        
        // Try to plan again
        project.planProject();
        assertEquals("CONSTRUCTION", project.getCurrentStateName());
        
        // Try to complete without inspection
        project.complete();
        assertEquals("CONSTRUCTION", project.getCurrentStateName());
    }
}
