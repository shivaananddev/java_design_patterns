package com.anpra.java_design_patterns.patterns.behavioral.mediator;

/**
 * Interface for construction site components that communicate through the mediator
 */
public interface SiteComponent {
    void send(String message);
    void receive(String from, String message);
    String getName();
}
