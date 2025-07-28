package com.anpra.java_design_patterns.solid.lsp;

import org.springframework.stereotype.Component;

@Component
public class LspExample {
    public String execute() {
        HouseWorker worker = new ConstructionWorker();
        return worker.performTask();
    }
}