package com.anpra.java_design_patterns.solid.dip;

public class ConcreteWorker implements WorkerService {
    public String doWork() {
        return "Doing concrete work";
    }
}