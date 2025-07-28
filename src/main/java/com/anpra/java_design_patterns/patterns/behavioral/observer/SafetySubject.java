package com.anpra.java_design_patterns.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject interface defining the contract for safety monitoring system
 */
public interface SafetySubject {
    void registerObserver(SafetyObserver observer);
    void removeObserver(SafetyObserver observer);
    void notifyObservers();
}
