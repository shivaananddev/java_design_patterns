package com.anpra.java_design_patterns.solid.dip;

public class BuilderService {
    private final WorkerService worker;

    public BuilderService(WorkerService worker) {
        this.worker = worker;
    }

    public String doWork() {
        return worker.doWork();
    }
}