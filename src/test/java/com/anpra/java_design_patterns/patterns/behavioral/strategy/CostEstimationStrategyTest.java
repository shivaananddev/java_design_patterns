package com.anpra.java_design_patterns.patterns.behavioral.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class CostEstimationStrategyTest {
    private CostEstimator estimator;
    private ConstructionProject project;
    
    @BeforeEach
    void setUp() {
        estimator = new CostEstimator();
        
        Map<String, Integer> materials = new HashMap<>();
        materials.put("concrete", 100);
        materials.put("steel", 50);
        materials.put("wood", 200);
        
        project = new ConstructionProject(2000.0, "residential", materials, 1000);
    }
    
    @Test
    void testSquareFootageStrategy() {
        estimator.setStrategy(new SquareFootageStrategy());
        double cost = estimator.estimateProjectCost(project);
        
        assertEquals(300000.0, cost); // 2000 sq ft * $150 per sq ft
    }
    
    @Test
    void testDetailedEstimationStrategy() {
        estimator.setStrategy(new DetailedEstimationStrategy());
        double cost = estimator.estimateProjectCost(project);
        
        // Materials: (100 * $100) + (50 * $500) + (200 * $50) = $35,000
        // Labor: 1000 hours * $75 = $75,000
        // Total: $110,000
        assertEquals(110000.0, cost);
    }
    
    @Test
    void testComparativeEstimationStrategy() {
        List<HistoricalProject> historicalProjects = Arrays.asList(
            new HistoricalProject("residential", 1000.0, 150000.0),
            new HistoricalProject("residential", 1500.0, 225000.0)
        );
        
        estimator.setStrategy(new ComparativeEstimationStrategy(historicalProjects));
        double cost = estimator.estimateProjectCost(project);
        
        assertTrue(cost > 0);
    }
    
    @Test
    void testStrategyNotSet() {
        Exception exception = assertThrows(IllegalStateException.class, 
            () -> estimator.estimateProjectCost(project));
        assertEquals("Estimation strategy not set", exception.getMessage());
    }
}
