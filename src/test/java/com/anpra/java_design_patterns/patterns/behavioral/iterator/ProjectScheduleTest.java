package com.anpra.java_design_patterns.patterns.behavioral.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

class ProjectScheduleTest {
    private ProjectSchedule schedule;

    @BeforeEach
    void setUp() {
        schedule = new ProjectSchedule();
        
        // Add tasks for different phases and priorities
        schedule.addTask(new ConstructionTask("Foundation work", 1, "Foundation", 10));
        schedule.addTask(new ConstructionTask("Wall framing", 2, "Structure", 15));
        schedule.addTask(new ConstructionTask("Electrical wiring", 1, "Electrical", 7));
        schedule.addTask(new ConstructionTask("Plumbing installation", 2, "Plumbing", 8));
        schedule.addTask(new ConstructionTask("Roof installation", 1, "Structure", 12));
    }

    @Test
    void testPhaseIterator() {
        TaskIterator iterator = schedule.getPhaseIterator("Structure");
        int count = 0;
        
        while (iterator.hasNext()) {
            ConstructionTask task = iterator.next();
            assertEquals("Structure", task.getPhase());
            count++;
        }
        
        assertEquals(2, count);
    }

    @Test
    void testPriorityIterator() {
        TaskIterator iterator = schedule.getPriorityIterator();
        ConstructionTask firstTask = iterator.next();
        
        assertEquals(1, firstTask.getPriority());
    }

    @Test
    void testScheduleIterator() {
        TaskIterator iterator = schedule.getScheduleIterator();
        int count = 0;
        
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        
        assertEquals(5, count);
    }

    @Test
    void testIteratorReset() {
        TaskIterator iterator = schedule.getScheduleIterator();
        ConstructionTask firstTask = iterator.next();
        
        while (iterator.hasNext()) {
            iterator.next();
        }
        
        iterator.reset();
        assertEquals(firstTask.getName(), iterator.next().getName());
    }

    @Test
    void testNoSuchElementException() {
        TaskIterator iterator = schedule.getPhaseIterator("NonexistentPhase");
        
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
