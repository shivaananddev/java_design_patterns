package com.anpra.java_design_patterns.solid.dip.goodcode;

/**
 * 🏗️ Analogy: House Construction (DIP Good Example)
 * High-level construction manager depends on abstractions (worker interfaces)
 * not concrete implementations (specific worker types)
 */
public interface Worker {
    void performTask();
}

public class ConcreteWorker implements Worker {
    @Override
    public void performTask() {
        System.out.println("Performing concrete work");
    }
}

public class ElectricalWorker implements Worker {
    @Override
    public void performTask() {
        System.out.println("Performing electrical work");
    }
}

public class PlumbingWorker implements Worker {
    @Override
    public void performTask() {
        System.out.println("Performing plumbing work");
    }
}

@Service
public class WorkerService {
    private final List<Worker> workers;

    public WorkerService(List<Worker> workers) {
        this.workers = workers;
    }

    public void executeWork() {
        workers.forEach(Worker::performTask);
    }
}

@Service
public class BuilderService {
    private final WorkerService workerService;

    public BuilderService(WorkerService workerService) {
        this.workerService = workerService;
    }

    public void buildHouse() {
        System.out.println("Starting house construction...");
        workerService.executeWork();
        System.out.println("House construction completed!");
    }
}
