package com.anpra.java_design_patterns.solid.lsp.badcode;

/**
 * 🏗️ Analogy: House Construction (LSP Bad Example)
 * Subtype (BadWorker) does not follow the contract of the base type (Worker) and breaks expected behavior.
 * This violates LSP, as substituting BadWorker for Worker causes runtime errors.
 */
public abstract class Worker {
    public abstract void workOnHouse();
}

public class BadWorker extends Worker {
    @Override
    public void workOnHouse() {
        throw new UnsupportedOperationException("BadWorker cannot work on a house!");
    }
}

public class LspExample {
    public void assignWork(Worker worker) {
        worker.workOnHouse();
    }
}
