package com.anpra.java_design_patterns.solid.isp.goodcode;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class ConstructionProjectTest {
    @Test
    void testExecuteConstruction() {
        // Arrange
        Painter painter = mock(Painter.class);
        Plumber plumber = mock(Plumber.class);
        Electrician electrician = mock(Electrician.class);
        
        ConstructionProject project = new ConstructionProject(painter, plumber, electrician);

        // Act
        project.executeConstruction();

        // Assert
        verify(plumber).installPipes();
        verify(electrician).installWiring();
        verify(painter).prepareArea();
        verify(painter).paint();
        verify(plumber).fixLeaks();
        verify(electrician).testCircuits();
    }
}
