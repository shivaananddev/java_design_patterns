package com.anpra.java_design_patterns.solid.isp.goodcode;

import org.junit.jupiter.api.Test;

class IspExampleTest {
    @Test
    void testBuildHouse() {
        IspExample example = new IspExample();
        example.buildHouse(new HousePainter(), new HouseElectrician(), new HousePlumber());
        // Output should show each expert doing their job
    }
}
