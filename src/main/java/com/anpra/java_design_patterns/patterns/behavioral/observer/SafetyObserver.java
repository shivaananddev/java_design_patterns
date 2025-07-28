package com.anpra.java_design_patterns.patterns.behavioral.observer;

/**
 * Observer interface for safety monitoring
 */
public interface SafetyObserver {
    void update(SafetyEvent event);
}
