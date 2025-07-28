package com.anpra.java_design_patterns.solid.srp.goodcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstructionManagerTest {
    @Test
    void testConstructHouse() {
        PlumbingService plumbing = new PlumbingService();
        WiringService wiring = new WiringService();
        FramingService framing = new FramingService();
        ConstructionManager manager = new ConstructionManager(plumbing, wiring, framing);
        manager.constructHouse();
        // No exceptions should be thrown, and output should show all steps
    }
}
