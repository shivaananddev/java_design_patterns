package com.anpra.java_design_patterns.patterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Construction crew component
 */
public class ConstructionCrew extends AbstractSiteComponent {
    private final String specialty;
    private boolean isAvailable;
    private final List<String> taskLog;

    public ConstructionCrew(ConstructionMediator mediator, String name, String specialty) {
        super(mediator, name);
        this.specialty = specialty;
        this.isAvailable = true;
        this.taskLog = new ArrayList<>();
    }

    @Override
    public void receive(String from, String message) {
        String logEntry = String.format("Message from %s: %s", from, message);
        taskLog.add(logEntry);
        
        if (message.toLowerCase().contains(specialty.toLowerCase())) {
            if (isAvailable) {
                sendTo(from, "Available for " + specialty + " work");
            } else {
                sendTo(from, "Currently busy with another task");
            }
        }
    }

    public void startWork() {
        isAvailable = false;
        send("Starting " + specialty + " work");
    }

    public void completeWork() {
        isAvailable = true;
        send("Completed " + specialty + " work, available for new tasks");
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public List<String> getTaskLog() {
        return new ArrayList<>(taskLog);
    }
}
