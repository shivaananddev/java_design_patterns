package com.anpra.java_design_patterns.patterns.behavioral.strategy;

/**
 * 🏗️ Analogy: Construction Cost Estimation Strategies
 * Like different methods for estimating construction costs (per square foot, detailed, etc.)
 */
public interface CostEstimationStrategy {
    double calculateCost(ConstructionProject project);
}

public class ConstructionProject {
    private final double squareFootage;
    private final String buildingType;
    private final Map<String, Integer> materials;
    private final int laborHours;
    
    public ConstructionProject(double squareFootage, String buildingType,
                             Map<String, Integer> materials, int laborHours) {
        this.squareFootage = squareFootage;
        this.buildingType = buildingType;
        this.materials = new HashMap<>(materials);
        this.laborHours = laborHours;
    }
    
    // Getters
    public double getSquareFootage() { return squareFootage; }
    public String getBuildingType() { return buildingType; }
    public Map<String, Integer> getMaterials() { return new HashMap<>(materials); }
    public int getLaborHours() { return laborHours; }
}

public class SquareFootageStrategy implements CostEstimationStrategy {
    private final Map<String, Double> ratePerSqFt = new HashMap<>();
    
    public SquareFootageStrategy() {
        ratePerSqFt.put("residential", 150.0);
        ratePerSqFt.put("commercial", 200.0);
        ratePerSqFt.put("industrial", 175.0);
    }
    
    @Override
    public double calculateCost(ConstructionProject project) {
        double rate = ratePerSqFt.getOrDefault(
            project.getBuildingType().toLowerCase(), 150.0);
        return project.getSquareFootage() * rate;
    }
}

public class DetailedEstimationStrategy implements CostEstimationStrategy {
    private final Map<String, Double> materialCosts = new HashMap<>();
    private final double laborCostPerHour;
    
    public DetailedEstimationStrategy() {
        materialCosts.put("concrete", 100.0);
        materialCosts.put("steel", 500.0);
        materialCosts.put("wood", 50.0);
        this.laborCostPerHour = 75.0;
    }
    
    @Override
    public double calculateCost(ConstructionProject project) {
        double materialCost = calculateMaterialCost(project.getMaterials());
        double laborCost = project.getLaborHours() * laborCostPerHour;
        return materialCost + laborCost;
    }
    
    private double calculateMaterialCost(Map<String, Integer> materials) {
        return materials.entrySet().stream()
            .mapToDouble(entry -> 
                materialCosts.getOrDefault(entry.getKey().toLowerCase(), 0.0) * entry.getValue())
            .sum();
    }
}

public class ComparativeEstimationStrategy implements CostEstimationStrategy {
    private final List<HistoricalProject> historicalProjects;
    
    public ComparativeEstimationStrategy(List<HistoricalProject> historicalProjects) {
        this.historicalProjects = new ArrayList<>(historicalProjects);
    }
    
    @Override
    public double calculateCost(ConstructionProject project) {
        return historicalProjects.stream()
            .filter(p -> p.getBuildingType().equals(project.getBuildingType()))
            .mapToDouble(p -> p.getCost() * (project.getSquareFootage() / p.getSquareFootage()))
            .average()
            .orElse(0.0);
    }
}

public class CostEstimator {
    private CostEstimationStrategy strategy;
    
    public void setStrategy(CostEstimationStrategy strategy) {
        this.strategy = strategy;
    }
    
    public double estimateProjectCost(ConstructionProject project) {
        if (strategy == null) {
            throw new IllegalStateException("Estimation strategy not set");
        }
        return strategy.calculateCost(project);
    }
}

// Helper class for comparative estimation
public class HistoricalProject {
    private final String buildingType;
    private final double squareFootage;
    private final double cost;
    
    public HistoricalProject(String buildingType, double squareFootage, double cost) {
        this.buildingType = buildingType;
        this.squareFootage = squareFootage;
        this.cost = cost;
    }
    
    // Getters
    public String getBuildingType() { return buildingType; }
    public double getSquareFootage() { return squareFootage; }
    public double getCost() { return cost; }
}
