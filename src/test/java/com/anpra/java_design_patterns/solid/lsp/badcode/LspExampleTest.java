package com.anpra.java_design_patterns.solid.lsp.badcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LspExampleTest {
    @Test
    void testAssignWorkWithBadWorker() {
        LspExample example = new LspExample();
        assertThrows(UnsupportedOperationException.class, () -> {
            example.assignWork(new BadWorker());
        });
        // Should throw exception, violating LSP
    }
}
