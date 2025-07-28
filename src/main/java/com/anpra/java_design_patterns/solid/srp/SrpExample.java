package com.anpra.java_design_patterns.solid.srp;

import org.springframework.stereotype.Component;

@Component
public class SrpExample {

    public String execute() {
        ConstructionTaskManager taskManager = new ConstructionTaskManager();
        taskManager.assignTask("Install plumbing");

        ConstructionLogger logger = new ConstructionLogger();
        logger.logTask("Install plumbing");

        return "SRP Executed: Task assigned and logged.";
    }
}