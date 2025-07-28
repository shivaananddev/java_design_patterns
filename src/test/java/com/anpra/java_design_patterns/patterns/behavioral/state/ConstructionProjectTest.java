package com.anpra.java_design_patterns.patterns.behavioral.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstructionProjectTest {
    private ConstructionProject project;

    @BeforeEach
    void setUp() {
        project = new ConstructionProject();
    }

    @Test
    void testInitialState() {
        assertEquals("Planning", project.getCurrentStateName());
    }

    @Test
    void testValidStateTransition() {
        project.planProject();
        assertEquals("Pre-Construction", project.getCurrentStateName());
        
        project.startConstruction();
        assertEquals("Construction", project.getCurrentStateName());
    }

    @Test
    void testInvalidOperationInPlanning() {
        Exception exception = assertThrows(IllegalStateException.class, 
            () -> project.startConstruction());
        assertEquals("Cannot start construction in planning phase", exception.getMessage());
    }

    @Test
    void testCompletionRequiresInspection() {
        // Move to construction phase
        project.planProject();
        project.startConstruction();

        Exception exception = assertThrows(IllegalStateException.class, 
            () -> project.completePhase());
        assertEquals("Inspection required before completion", exception.getMessage());

        // Perform inspection and then complete
        project.inspectWork();
        project.completePhase();
        assertEquals("Completion", project.getCurrentStateName());
    }

    @Test
    void testProjectLogTracking() {
        project.planProject();
        project.startConstruction();
        
        List<String> log = project.getProjectLog();
        assertTrue(log.stream().anyMatch(entry -> entry.contains("State changed to: Pre-Construction")));
        assertTrue(log.stream().anyMatch(entry -> entry.contains("State changed to: Construction")));
    }

    @Test
    void testTaskCompletion() {
        project.planProject();
        assertTrue(project.isTaskCompleted("Project Planning"));
        
        project.startConstruction();
        assertTrue(project.isTaskCompleted("Permits Obtained"));
        assertTrue(project.isTaskCompleted("Materials Ordered"));
    }

    @Test
    void testStateTimestampTracking() {
        LocalDateTime initialTimestamp = project.getLastStateChange();
        project.planProject();
        LocalDateTime newTimestamp = project.getLastStateChange();
        
        assertTrue(newTimestamp.isAfter(initialTimestamp));
    }
}
