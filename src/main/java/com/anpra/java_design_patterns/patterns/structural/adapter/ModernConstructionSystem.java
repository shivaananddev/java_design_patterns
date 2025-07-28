package com.anpra.java_design_patterns.patterns.structural.adapter;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Modern interface for construction management system
 */
public interface ModernConstructionSystem {
    CompletableFuture<String> submitBlueprintAsync(Blueprint blueprint);
    CompletableFuture<PermitStatus> checkPermitStatusAsync(Permit permit);
    CompletableFuture<MaterialCostBreakdown> calculateDetailedMaterialCosts(MaterialSpecification specs);
    
    record Blueprint(String data, String version, String format) {}
    record Permit(String number, String type, String issueDate) {}
    record MaterialSpecification(String materials, double quantity, String unit) {}
    record MaterialCostBreakdown(double totalCost, double laborCost, double materialCost, double overheadCost) {}
    record PermitStatus(String status, String lastUpdated, String nextReviewDate) {}
}
