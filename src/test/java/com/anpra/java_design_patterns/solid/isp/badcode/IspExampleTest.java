package com.anpra.java_design_patterns.solid.isp.badcode;

import org.junit.jupiter.api.Test;

class IspExampleTest {
    @Test
    void testBuildHouse() {
        IspExample example = new IspExample();
        example.buildHouse(new BadPainter());
        example.buildHouse(new BadElectrician());
        example.buildHouse(new BadPlumber());
        // Output should show unnecessary method calls
    }
}
