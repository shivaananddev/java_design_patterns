package com.anpra.java_design_patterns.solid.ocp.badcode;

import org.junit.jupiter.api.Test;

class HouseBuilderTest {
    @Test
    void testBuildComponents() {
        HouseBuilder builder = new HouseBuilder();
        builder.build("plumbing");
        builder.build("electrical");
        builder.build("solar");
        builder.build("unknown");
        // Output should show installation and unknown type
    }
}
