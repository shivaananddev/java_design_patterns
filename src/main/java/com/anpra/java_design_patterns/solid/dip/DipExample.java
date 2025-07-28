package com.anpra.java_design_patterns.solid.dip;

import org.springframework.stereotype.Component;

@Component
public class DipExample {
    public String execute() {
        WorkerService worker = new BuilderService(new ConcreteWorker());
        return worker.doWork();
    }
}