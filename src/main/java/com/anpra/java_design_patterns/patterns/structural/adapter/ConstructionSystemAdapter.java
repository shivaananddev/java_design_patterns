package com.anpra.java_design_patterns.patterns.structural.adapter;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Adapter to make legacy construction system work with modern interface
 * Implements robust error handling, retry mechanisms, and asynchronous operations
 */
public class ConstructionSystemAdapter implements ModernConstructionSystem {
    private static final Logger LOGGER = Logger.getLogger(ConstructionSystemAdapter.class.getName());
    private final LegacyConstructionSystem legacySystem;
    private final ScheduledExecutorService executorService;
    private static final int MAX_RETRIES = 3;
    private static final long RETRY_DELAY_MS = 1000;

    public ConstructionSystemAdapter(LegacyConstructionSystem legacySystem) {
        this.legacySystem = legacySystem;
        this.executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public CompletableFuture<String> submitBlueprintAsync(Blueprint blueprint) {
        return CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Submitting blueprint version " + blueprint.version());
            return retryWithBackoff(() -> legacySystem.submitBlueprint(blueprint.data()));
        }, executorService);
    }

    @Override
    public CompletableFuture<PermitStatus> checkPermitStatusAsync(Permit permit) {
        return CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Checking permit status for " + permit.number());
            boolean status = retryWithBackoff(() -> legacySystem.checkPermitStatus(permit.number()));
            return new PermitStatus(
                status ? "APPROVED" : "PENDING",
                LocalDateTime.now().toString(),
                LocalDateTime.now().plusDays(7).toString()
            );
        }, executorService);
    }

    @Override
    public CompletableFuture<MaterialCostBreakdown> calculateDetailedMaterialCosts(MaterialSpecification specs) {
        return CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Calculating costs for " + specs.materials());
            double totalCost = retryWithBackoff(() -> legacySystem.calculateMaterialCost(specs.materials()));
            
            // Enhanced calculation with modern breakdown
            double materialCost = totalCost * 0.7;
            double laborCost = totalCost * 0.2;
            double overheadCost = totalCost * 0.1;
            
            return new MaterialCostBreakdown(totalCost, laborCost, materialCost, overheadCost);
        }, executorService);
    }

    private <T> T retryWithBackoff(RetryableOperation<T> operation) {
        int attempts = 0;
        while (attempts < MAX_RETRIES) {
            try {
                return operation.execute();
            } catch (Exception e) {
                attempts++;
                if (attempts == MAX_RETRIES) {
                    LOGGER.log(Level.SEVERE, "Operation failed after " + MAX_RETRIES + " attempts", e);
                    throw new RuntimeException("Operation failed after retries", e);
                }
                LOGGER.warning("Attempt " + attempts + " failed, retrying...");
                try {
                    Thread.sleep(RETRY_DELAY_MS * attempts);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Retry interrupted", ie);
                }
            }
        }
        throw new RuntimeException("Should not reach here");
    }

    @FunctionalInterface
    private interface RetryableOperation<T> {
        T execute() throws Exception;
    }

    // Cleanup method to be called when adapter is no longer needed
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
