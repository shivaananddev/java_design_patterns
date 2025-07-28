package com.anpra.java_design_patterns.solid.srp.badcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HandymanServiceTest {
    @Test
    void testConstructHouse() {
        HandymanService handyman = new HandymanService();
        handyman.constructHouse();
        // No exceptions should be thrown, but this class violates SRP
    }
}
