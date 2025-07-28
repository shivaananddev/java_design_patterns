package com.anpra.java_design_patterns.patterns.creational.abstractfactory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaterialFactoryTest {
    @Test
    void testResidentialMaterials() {
        MaterialFactory factory = new ResidentialMaterialFactory();
        
        Concrete concrete = factory.createConcrete();
        Steel steel = factory.createSteel();
        Glass glass = factory.createGlass();
        
        assertEquals("3000 PSI", concrete.getStrength());
        assertEquals("Grade 40", steel.getGrade());
        assertEquals("6mm", glass.getThickness());
    }

    @Test
    void testCommercialMaterials() {
        MaterialFactory factory = new CommercialMaterialFactory();
        
        Concrete concrete = factory.createConcrete();
        Steel steel = factory.createSteel();
        Glass glass = factory.createGlass();
        
        assertEquals("5000 PSI", concrete.getStrength());
        assertEquals("Grade 60", steel.getGrade());
        assertEquals("12mm", glass.getThickness());
    }

    @Test
    void testMaterialCreation() {
        MaterialFactory residential = new ResidentialMaterialFactory();
        MaterialFactory commercial = new CommercialMaterialFactory();
        
        assertTrue(residential.createConcrete() instanceof ResidentialConcrete);
        assertTrue(commercial.createConcrete() instanceof CommercialConcrete);
    }
}
