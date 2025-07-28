package com.anpra.java_design_patterns.patterns.structural.adapter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PowerToolAdapterTest {
    @Test
    void testAdapterPattern() {
        // Test modern tool directly
        ModernPowerTool modernTool = new ModernConstructionTool();
        assertTrue(modernTool.isCharged());
        
        // Test legacy tool through adapter
        LegacyPowerTool legacyTool = new LegacyPowerTool();
        ModernPowerTool adaptedTool = new PowerToolAdapter(legacyTool);
        
        assertTrue(adaptedTool.isCharged());
    }
    
    @Test
    void testCompatibility() {
        LegacyPowerTool legacyTool = new LegacyPowerTool();
        ModernPowerTool adaptedTool = new PowerToolAdapter(legacyTool);
        
        // Verify that adapted tool works with modern system
        assertDoesNotThrow(() -> adaptedTool.useModernTool());
    }
}
