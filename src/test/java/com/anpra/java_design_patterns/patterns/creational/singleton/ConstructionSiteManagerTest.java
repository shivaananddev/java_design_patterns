package com.anpra.java_design_patterns.patterns.creational.singleton;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstructionSiteManagerTest {
    @Test
    void testSingletonInstance() {
        ConstructionSiteManager manager1 = ConstructionSiteManager.getInstance();
        ConstructionSiteManager manager2 = ConstructionSiteManager.getInstance();
        
        assertSame(manager1, manager2, "Both instances should be the same");
    }

    @Test
    void testProjectManagement() {
        ConstructionSiteManager manager = ConstructionSiteManager.getInstance();
        
        Project project = new Project("1", "Residential Complex");
        manager.addProject("1", project);
        
        assertEquals(project, manager.getProject("1"));
        assertEquals(1, manager.getAllProjects().size());
    }

    @Test
    void testProjectStatus() {
        Project project = new Project("1", "Commercial Building");
        assertEquals(ProjectStatus.NEW, project.getStatus());
        
        project.updateStatus(ProjectStatus.IN_PROGRESS);
        assertEquals(ProjectStatus.IN_PROGRESS, project.getStatus());
    }
}
