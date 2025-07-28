package com.anpra.java_design_patterns.solid.lsp.goodcode;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class ConstructionSiteTest {
    @Test
    void testAddingDifferentWorkers() {
        // Arrange
        ConstructionSite site = new ConstructionSite();
        ConstructionWorker generalWorker = mock(GeneralWorker.class);
        ConstructionWorker specializedWorker = mock(SpecializedWorker.class);
        
        when(generalWorker.isQualified()).thenReturn(true);
        when(specializedWorker.isQualified()).thenReturn(true);

        // Act
        site.addWorker(generalWorker);
        site.addWorker(specializedWorker);
        site.startWork();

        // Assert
        verify(generalWorker).work();
        verify(specializedWorker).work();
    }

    @Test
    void testUnqualifiedWorkerNotAdded() {
        // Arrange
        ConstructionSite site = new ConstructionSite();
        ConstructionWorker unqualifiedWorker = mock(ConstructionWorker.class);
        when(unqualifiedWorker.isQualified()).thenReturn(false);

        // Act
        site.addWorker(unqualifiedWorker);
        site.startWork();

        // Assert
        verify(unqualifiedWorker, never()).work();
    }
}
