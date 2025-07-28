package com.anpra.java_design_patterns.solid.lsp.goodcode;

/**
 * 🏗️ Analogy: House Construction (LSP Good Example)
 * Subtypes (e.g., ConstructionWorker, HouseWorker) can be substituted for their base type (Worker) without affecting correctness.
 * All workers follow the contract: they can work on a house.
 */
public abstract class Worker {
    public abstract void workOnHouse();
}

public class ConstructionWorker extends Worker {
    @Override
    public void workOnHouse() {
        System.out.println("Construction worker builds the house structure.");
    }
}

public class HouseWorker extends Worker {
    @Override
    public void workOnHouse() {
        System.out.println("House worker paints and finishes the house.");
    }
}

public class LspExample {
    public void assignWork(Worker worker) {
        worker.workOnHouse();
    }
}
