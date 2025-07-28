package com.anpra.java_design_patterns.patterns.behavioral.state;

/**
 * State interface for construction project phases
 */
public interface ProjectState {
    void planProject();
    void startConstruction();
    void inspect();
    void complete();
    String getStateName();
}
