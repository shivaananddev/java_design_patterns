package com.anpra.java_design_patterns.patterns.behavioral.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Custom iterator interface for construction schedule
 */
public interface ConstructionScheduleIterator extends Iterator<ConstructionTask> {
    boolean hasNext();
    ConstructionTask next();
    void reset();
    
    // Optional: Add specialized iteration methods
    boolean hasPreviousTask();
    ConstructionTask previousTask();
    ConstructionTask peekNext();
}
