package com.anpra.java_design_patterns.patterns.behavioral.memento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class ConstructionPlanTest {
    private ConstructionPlan plan;
    private PlanCaretaker caretaker;

    @BeforeEach
    void setUp() {
        plan = new ConstructionPlan();
        caretaker = new PlanCaretaker();
    }

    @Test
    void testSaveAndRestorePlan() {
        // Initial state
        Map<String, Double> materials = new HashMap<>();
        materials.put("concrete", 100.0);
        List<String> steps = Arrays.asList("Foundation", "Walls");
        
        plan.updatePlan("v1.0", materials, steps, 50000.0);
        caretaker.save(plan);

        // Update plan
        materials.put("steel", 50.0);
        steps = Arrays.asList("Foundation", "Walls", "Roof");
        plan.updatePlan("v2.0", materials, steps, 75000.0);

        // Verify current state
        assertEquals("v2.0", plan.getBlueprintVersion());
        assertEquals(75000.0, plan.getEstimatedCost());

        // Restore previous state
        caretaker.undo(plan);

        // Verify restored state
        assertEquals("v1.0", plan.getBlueprintVersion());
        assertEquals(50000.0, plan.getEstimatedCost());
    }

    @Test
    void testUndoRedoFunctionality() {
        // First version
        plan.updatePlan("v1.0", new HashMap<>(), new ArrayList<>(), 50000.0);
        caretaker.save(plan);

        // Second version
        plan.updatePlan("v2.0", new HashMap<>(), new ArrayList<>(), 75000.0);
        caretaker.save(plan);

        // Third version
        plan.updatePlan("v3.0", new HashMap<>(), new ArrayList<>(), 100000.0);
        caretaker.save(plan);

        // Undo to v2.0
        caretaker.undo(plan);
        assertEquals("v2.0", plan.getBlueprintVersion());

        // Redo to v3.0
        caretaker.redo(plan);
        assertEquals("v3.0", plan.getBlueprintVersion());
    }

    @Test
    void testHistoryManagement() {
        plan.updatePlan("v1.0", new HashMap<>(), new ArrayList<>(), 50000.0);
        caretaker.save(plan);
        
        assertEquals(1, caretaker.getHistorySize());
        assertEquals(0, caretaker.getRedoHistorySize());

        caretaker.clearHistory();
        assertEquals(0, caretaker.getHistorySize());
    }
}
