package com.anpra.java_design_patterns.solid.ocp.goodcode;

import org.junit.jupiter.api.Test;

class HouseBuilderTest {
    @Test
    void testBuildComponents() {
        HouseBuilder builder = new HouseBuilder();
        builder.build(new PlumbingComponent());
        builder.build(new ElectricalComponent());
        builder.build(new SolarPanelComponent());
        // Output should show installation of each component
    }
}
