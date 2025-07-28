package com.anpra.java_design_patterns.patterns.structural.bridge;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class ConstructionProjectTest {
    @Test
    void testResidentialProjectWithAgileManagement() {
        ProjectManagementMethod agileMethod = mock(AgileManagement.class);
        ConstructionProject project = new ResidentialProject(agileMethod);
        
        project.startProject();
        project.trackProgress();
        
        verify(agileMethod).planProject();
        verify(agileMethod).executeProject();
        verify(agileMethod).monitorProgress();
    }
    
    @Test
    void testCommercialProjectWithWaterfallManagement() {
        ProjectManagementMethod waterfallMethod = mock(WaterfallManagement.class);
        ConstructionProject project = new CommercialProject(waterfallMethod);
        
        project.startProject();
        project.trackProgress();
        
        verify(waterfallMethod).planProject();
        verify(waterfallMethod).executeProject();
        verify(waterfallMethod).monitorProgress();
    }
}
