package com.anpra.java_design_patterns.patterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Site supervisor component
 */
public class SiteSupervisor extends AbstractSiteComponent {
    private final List<String> supervisorLog;
    private final List<String> openRequests;

    public SiteSupervisor(ConstructionMediator mediator, String name) {
        super(mediator, name);
        this.supervisorLog = new ArrayList<>();
        this.openRequests = new ArrayList<>();
    }

    @Override
    public void receive(String from, String message) {
        String logEntry = String.format("Message from %s: %s", from, message);
        supervisorLog.add(logEntry);

        if (message.contains("available")) {
            openRequests.removeIf(request -> request.contains(from));
        } else if (message.contains("completed")) {
            sendTo(from, "Acknowledged completion. Stand by for inspection.");
        }
    }

    public void requestWork(String crewName, String workType) {
        String request = String.format("Need %s work - crew: %s", workType, crewName);
        openRequests.add(request);
        sendTo(crewName, request);
    }

    public void broadcastSafetyAlert(String alert) {
        send("SAFETY ALERT: " + alert);
    }

    public void scheduleInspection(String location) {
        send("Inspection scheduled for " + location);
    }

    public List<String> getOpenRequests() {
        return new ArrayList<>(openRequests);
    }

    public List<String> getSupervisorLog() {
        return new ArrayList<>(supervisorLog);
    }
}
