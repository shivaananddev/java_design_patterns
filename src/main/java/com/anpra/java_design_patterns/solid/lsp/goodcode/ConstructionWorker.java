package com.anpra.java_design_patterns.solid.lsp.goodcode;

/**
 * 🏗️ Analogy: House Construction (LSP Good Example)
 * Any specialized construction worker should be able to replace a general construction worker
 * without breaking the construction process.
 */
public interface ConstructionWorker {
    void work();
    boolean isQualified();
}

public class GeneralWorker implements ConstructionWorker {
    @Override
    public void work() {
        System.out.println("Doing general construction work");
    }

    @Override
    public boolean isQualified() {
        return true;
    }
}

public class SpecializedWorker implements ConstructionWorker {
    private final String specialty;

    public SpecializedWorker(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public void work() {
        System.out.println("Doing specialized " + specialty + " work");
    }

    @Override
    public boolean isQualified() {
        return true;
    }
}

public class ConstructionSite {
    private final List<ConstructionWorker> workers = new ArrayList<>();

    public void addWorker(ConstructionWorker worker) {
        if (worker.isQualified()) {
            workers.add(worker);
        }
    }

    public void startWork() {
        workers.forEach(ConstructionWorker::work);
    }
}
