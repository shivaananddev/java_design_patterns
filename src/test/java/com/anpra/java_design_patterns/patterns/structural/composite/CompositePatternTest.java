package com.anpra.java_design_patterns.patterns.structural.composite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Duration;

class CompositePatternTest {
    private ProjectPhase mainProject;
    private ProjectPhase foundation;
    private ProjectPhase structure;
    private ConstructionTask excavation;
    private ConstructionTask concrete;
    private ConstructionTask framing;

    @BeforeEach
    void setUp() {
        // Create project structure
        mainProject = new ProjectPhase("Office Building Construction");
        foundation = new ProjectPhase("Foundation Phase");
        structure = new ProjectPhase("Structural Phase");

        excavation = new ConstructionTask(
            "Site Excavation",
            Duration.ofDays(5),
            new BigDecimal("15000"),
            "Prepare site for foundation"
        );

        concrete = new ConstructionTask(
            "Concrete Pouring",
            Duration.ofDays(3),
            new BigDecimal("25000"),
            "Pour and cure foundation"
        );

        framing = new ConstructionTask(
            "Steel Framing",
            Duration.ofDays(10),
            new BigDecimal("50000"),
            "Erect structural steel frame"
        );

        // Build project hierarchy
        mainProject.addComponent(foundation);
        mainProject.addComponent(structure);
        
        foundation.addComponent(excavation);
        foundation.addComponent(concrete);
        structure.addComponent(framing);

        // Set dependencies
        concrete.addDependency(excavation);
        framing.addDependency(concrete);
    }

    @Test
    void testProjectHierarchy() {
        assertEquals(2, mainProject.getComponents().size());
        assertEquals(2, foundation.getComponents().size());
        assertEquals(1, structure.getComponents().size());
    }

    @Test
    void testCostCalculation() {
        BigDecimal expectedCost = new BigDecimal("90000"); // 15000 + 25000 + 50000
        assertEquals(expectedCost, mainProject.getCost());
    }

    @Test
    void testDurationCalculation() {
        Duration expectedDuration = Duration.ofDays(18); // 5 + 3 + 10
        assertEquals(expectedDuration, mainProject.getDuration());
    }

    @Test
    void testDependencyValidation() {
        // Try to complete concrete before excavation
        excavation.markComplete();
        concrete.markComplete();
        framing.markComplete();

        // Should be able to complete all phases now
        foundation.markComplete();
        structure.markComplete();
        mainProject.markComplete();
        
        assertTrue(mainProject.isComplete());
    }

    @Test
    void testInvalidCompletion() {
        assertThrows(IllegalStateException.class, () -> {
            // Try to complete foundation before its tasks are complete
            foundation.markComplete();
        });
    }

    @Test
    void testProjectAnalysis() {
        ProjectAnalyzer analyzer = new ProjectAnalyzer();
        mainProject.accept(analyzer);

        assertEquals(3, analyzer.getTotalTasks());
        assertEquals(0, analyzer.getCompletedTasks());
        assertEquals(new BigDecimal("90000"), analyzer.getTotalCost());
        assertTrue(analyzer.getCriticalPath().size() > 0);
    }

    @Test
    void testComponentRemoval() {
        foundation.removeComponent(excavation);
        assertEquals(1, foundation.getComponents().size());
        assertNull(excavation.getParent());
    }
}
