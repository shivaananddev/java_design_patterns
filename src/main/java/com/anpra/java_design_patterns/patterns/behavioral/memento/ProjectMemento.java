package com.anpra.java_design_patterns.patterns.behavioral.memento;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Memento class representing a snapshot of construction project state
 */
public final class ProjectMemento {
    private final String state;
    private final LocalDateTime timestamp;
    private final String version;
    private final Map<String, Object> metadata;

    public ProjectMemento(String state, Map<String, Object> metadata) {
        this.state = state;
        this.timestamp = LocalDateTime.now();
        this.version = UUID.randomUUID().toString();
        this.metadata = new HashMap<>(metadata);
    }

    // Private getters for originator
    String getState() {
        return state;
    }

    LocalDateTime getTimestamp() {
        return timestamp;
    }

    String getVersion() {
        return version;
    }

    Map<String, Object> getMetadata() {
        return Collections.unmodifiableMap(metadata);
    }
}
