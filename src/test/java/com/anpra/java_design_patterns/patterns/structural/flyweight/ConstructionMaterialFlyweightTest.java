package com.anpra.java_design_patterns.patterns.structural.flyweight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConstructionMaterialFlyweightTest {
    @Test
    void testMaterialReuse() {
        ConstructionMaterialFlyweight material1 = ConstructionMaterialFactory.getMaterial("concrete");
        ConstructionMaterialFlyweight material2 = ConstructionMaterialFactory.getMaterial("concrete");
        
        assertSame(material1, material2, "Should return the same flyweight object");
    }
    
    @Test
    void testMultipleMaterialTypes() {
        ConstructionMaterialFactory.getMaterial("concrete");
        ConstructionMaterialFactory.getMaterial("steel");
        ConstructionMaterialFactory.getMaterial("wood");
        
        assertEquals(3, ConstructionMaterialFactory.getMaterialTypesCount());
    }
    
    @Test
    void testConstructionSiteUsage() {
        ConstructionSite site = new ConstructionSite();
        
        site.useMaterial("concrete", "Foundation", 100);
        site.useMaterial("steel", "Framework", 50);
        site.useMaterial("concrete", "Floor", 75);
        
        assertEquals(2, site.getTotalMaterialTypes());
    }
    
    @Test
    void testInvalidMaterialType() {
        assertThrows(IllegalArgumentException.class, 
            () -> ConstructionMaterialFactory.getMaterial("invalid"));
    }
}
