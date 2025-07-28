package com.anpra.java_design_patterns.patterns.behavioral.memento;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 🏗️ Analogy: Construction Project Versioning
 * Like keeping track of different versions of a construction plan,
 * allowing rollback to previous versions if needed
 */
public class ConstructionPlan {
    private String blueprintVersion;
    private Map<String, Double> materials;
    private List<String> constructionSteps;
    private double estimatedCost;
    private LocalDateTime lastModified;

    public ConstructionPlan() {
        this.materials = new HashMap<>();
        this.constructionSteps = new ArrayList<>();
    }

    public void updatePlan(String blueprintVersion, Map<String, Double> materials,
                          List<String> steps, double estimatedCost) {
        this.blueprintVersion = blueprintVersion;
        this.materials = new HashMap<>(materials);
        this.constructionSteps = new ArrayList<>(steps);
        this.estimatedCost = estimatedCost;
        this.lastModified = LocalDateTime.now();
    }

    public PlanMemento save() {
        return new PlanMemento(
            blueprintVersion,
            new HashMap<>(materials),
            new ArrayList<>(constructionSteps),
            estimatedCost,
            lastModified
        );
    }

    public void restore(PlanMemento memento) {
        this.blueprintVersion = memento.getBlueprintVersion();
        this.materials = new HashMap<>(memento.getMaterials());
        this.constructionSteps = new ArrayList<>(memento.getConstructionSteps());
        this.estimatedCost = memento.getEstimatedCost();
        this.lastModified = memento.getLastModified();
    }

    // Getters
    public String getBlueprintVersion() { return blueprintVersion; }
    public Map<String, Double> getMaterials() { return new HashMap<>(materials); }
    public List<String> getConstructionSteps() { return new ArrayList<>(constructionSteps); }
    public double getEstimatedCost() { return estimatedCost; }
    public LocalDateTime getLastModified() { return lastModified; }
}

public class PlanMemento {
    private final String blueprintVersion;
    private final Map<String, Double> materials;
    private final List<String> constructionSteps;
    private final double estimatedCost;
    private final LocalDateTime lastModified;

    public PlanMemento(String blueprintVersion, Map<String, Double> materials,
                      List<String> constructionSteps, double estimatedCost,
                      LocalDateTime lastModified) {
        this.blueprintVersion = blueprintVersion;
        this.materials = materials;
        this.constructionSteps = constructionSteps;
        this.estimatedCost = estimatedCost;
        this.lastModified = lastModified;
    }

    // Getters
    String getBlueprintVersion() { return blueprintVersion; }
    Map<String, Double> getMaterials() { return materials; }
    List<String> getConstructionSteps() { return constructionSteps; }
    double getEstimatedCost() { return estimatedCost; }
    LocalDateTime getLastModified() { return lastModified; }
}

public class PlanCaretaker {
    private final Stack<PlanMemento> history = new Stack<>();
    private final Stack<PlanMemento> redoHistory = new Stack<>();

    public void save(ConstructionPlan plan) {
        history.push(plan.save());
        redoHistory.clear(); // Clear redo history when a new save is made
    }

    public void undo(ConstructionPlan plan) {
        if (!history.isEmpty()) {
            redoHistory.push(plan.save());
            plan.restore(history.pop());
        }
    }

    public void redo(ConstructionPlan plan) {
        if (!redoHistory.isEmpty()) {
            history.push(plan.save());
            plan.restore(redoHistory.pop());
        }
    }

    public int getHistorySize() {
        return history.size();
    }

    public int getRedoHistorySize() {
        return redoHistory.size();
    }

    public void clearHistory() {
        history.clear();
        redoHistory.clear();
    }
}
