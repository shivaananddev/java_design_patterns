package com.anpra.java_design_patterns.patterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Safety inspector component
 */
public class SafetyInspector extends AbstractSiteComponent {
    private final List<String> inspectionLog;
    private boolean onSite;

    public SafetyInspector(ConstructionMediator mediator, String name) {
        super(mediator, name);
        this.inspectionLog = new ArrayList<>();
        this.onSite = false;
    }

    @Override
    public void receive(String from, String message) {
        String logEntry = String.format("Message from %s: %s", from, message);
        inspectionLog.add(logEntry);

        if (message.toLowerCase().contains("safety alert")) {
            respondToSafetyAlert(from);
        } else if (message.toLowerCase().contains("inspection")) {
            scheduleInspection(from);
        }
    }

    public void startInspection(String area) {
        onSite = true;
        send("Starting safety inspection in " + area);
    }

    public void completeInspection(String area, boolean passed) {
        String result = passed ? "PASSED" : "FAILED";
        send("Area " + area + " inspection complete - " + result);
        onSite = false;
    }

    private void respondToSafetyAlert(String from) {
        if (onSite) {
            sendTo(from, "Currently on site, responding to alert immediately");
        } else {
            sendTo(from, "Proceeding to site for safety assessment");
            onSite = true;
        }
    }

    private void scheduleInspection(String from) {
        sendTo(from, "Inspection request acknowledged, will coordinate timing");
    }

    public List<String> getInspectionLog() {
        return new ArrayList<>(inspectionLog);
    }

    public boolean isOnSite() {
        return onSite;
    }
}
