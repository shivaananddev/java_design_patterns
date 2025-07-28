package com.anpra.java_design_patterns.patterns.behavioral.template;

/**
 * Demonstration of the Template Method Pattern using construction examples
 */
public class TemplateMethodDemo {
    public static void main(String[] args) {
        System.out.println("=== Residential Construction Demo ===");
        // Standard residential construction
        ConstructionTemplate standardHome = new ResidentialConstruction(false);
        System.out.println("\nBuilding standard residential home:");
        standardHome.buildStructure();

        // Luxury residential construction
        System.out.println("\nBuilding luxury residential home:");
        ConstructionTemplate luxuryHome = new ResidentialConstruction(true);
        luxuryHome.buildStructure();

        System.out.println("\n=== Commercial Construction Demo ===");
        // Small office building
        ConstructionTemplate smallOffice = new CommercialConstruction(2, false);
        System.out.println("\nBuilding small office complex:");
        smallOffice.buildStructure();

        // Premium high-rise building
        System.out.println("\nBuilding premium high-rise:");
        ConstructionTemplate highRise = new CommercialConstruction(20, true);
        highRise.buildStructure();

        System.out.println("\n=== Industrial Construction Demo ===");
        // Standard warehouse
        ConstructionTemplate warehouse = new IndustrialConstruction("Warehouse", false);
        System.out.println("\nBuilding standard warehouse:");
        warehouse.buildStructure();

        // Chemical plant with hazard protection
        System.out.println("\nBuilding chemical plant:");
        ConstructionTemplate chemicalPlant = new IndustrialConstruction("Chemical Plant", true);
        chemicalPlant.buildStructure();
    }
}
