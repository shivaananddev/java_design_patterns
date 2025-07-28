package com.anpra.java_design_patterns.patterns.structural.adapter;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

/**
 * Legacy system interface representing old construction management software
 */
public interface LegacyConstructionSystem {
    String submitBlueprint(String blueprintData);
    boolean checkPermitStatus(String permitNumber);
    double calculateMaterialCost(String materialList);
}
