package com.anpra.java_design_patterns.solid.dip.goodcode;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import static org.mockito.Mockito.*;

class DipExampleTest {
    @Test
    void testBuilderService() {
        // Arrange
        Worker worker1 = mock(Worker.class);
        Worker worker2 = mock(Worker.class);
        WorkerService workerService = new WorkerService(Arrays.asList(worker1, worker2));
        BuilderService builderService = new BuilderService(workerService);

        // Act
        builderService.buildHouse();

        // Assert
        verify(worker1, times(1)).performTask();
        verify(worker2, times(1)).performTask();
    }

    @Test
    void testWorkerService() {
        // Arrange
        Worker worker1 = mock(Worker.class);
        Worker worker2 = mock(Worker.class);
        WorkerService workerService = new WorkerService(Arrays.asList(worker1, worker2));

        // Act
        workerService.executeWork();

        // Assert
        verify(worker1, times(1)).performTask();
        verify(worker2, times(1)).performTask();
    }
}
