package com.anpra.java_design_patterns.patterns.behavioral.mediator;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Concrete mediator for construction site coordination
 */
public class ConstructionSiteMediator implements ConstructionMediator {
    private static final Logger LOGGER = Logger.getLogger(ConstructionSiteMediator.class.getName());
    private final Map<String, SiteComponent> components;
    private final List<String> messageLog;

    public ConstructionSiteMediator() {
        this.components = new ConcurrentHashMap<>();
        this.messageLog = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public void registerComponent(SiteComponent component) {
        if (component != null) {
            components.put(component.getName(), component);
            logEvent("Registered new component: " + component.getName());
        }
    }

    @Override
    public void sendMessage(String from, String message) {
        logEvent(String.format("Broadcast from %s: %s", from, message));
        components.values().stream()
                 .filter(c -> !c.getName().equals(from))
                 .forEach(c -> c.receive(from, message));
    }

    @Override
    public void sendMessageTo(String from, String to, String message) {
        SiteComponent recipient = components.get(to);
        if (recipient != null) {
            logEvent(String.format("Direct message from %s to %s: %s", from, to, message));
            recipient.receive(from, message);
        } else {
            logEvent(String.format("Failed to deliver message from %s: recipient %s not found", from, to));
        }
    }

    public List<String> getMessageLog() {
        return new ArrayList<>(messageLog);
    }

    private void logEvent(String event) {
        String logEntry = String.format("[%tF %tT] %s", new Date(), new Date(), event);
        messageLog.add(logEntry);
        LOGGER.info(logEntry);
    }
}
