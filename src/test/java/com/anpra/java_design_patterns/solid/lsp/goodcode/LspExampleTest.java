package com.anpra.java_design_patterns.solid.lsp.goodcode;

import org.junit.jupiter.api.Test;

class LspExampleTest {
    @Test
    void testAssignWork() {
        LspExample example = new LspExample();
        example.assignWork(new ConstructionWorker());
        example.assignWork(new HouseWorker());
        // Output should show both workers performing their tasks
    }
}
