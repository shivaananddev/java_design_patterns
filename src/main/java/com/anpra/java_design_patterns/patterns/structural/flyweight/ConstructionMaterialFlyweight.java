package com.anpra.java_design_patterns.patterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 🏗️ Analogy: Construction Material Templates
 * Like reusing standard construction material specifications across multiple projects
 */
public class ConstructionMaterialFlyweight {
    private final String materialType;
    private final String specification;
    private final String manufacturer;
    
    // Intrinsic state - shared
    public ConstructionMaterialFlyweight(String materialType, String specification, String manufacturer) {
        this.materialType = materialType;
        this.specification = specification;
        this.manufacturer = manufacturer;
    }
    
    public void use(MaterialContext context) {
        System.out.printf("Using %s material (spec: %s, manufacturer: %s) at location: %s%n",
                         materialType, specification, manufacturer, context.getLocation());
    }
}

// Context class containing extrinsic state
class MaterialContext {
    private final String location;
    private final int quantity;
    
    public MaterialContext(String location, int quantity) {
        this.location = location;
        this.quantity = quantity;
    }
    
    public String getLocation() {
        return location;
    }
    
    public int getQuantity() {
        return quantity;
    }
}

// Flyweight factory
public class ConstructionMaterialFactory {
    private static final Map<String, ConstructionMaterialFlyweight> materials = new HashMap<>();
    
    public static ConstructionMaterialFlyweight getMaterial(String materialType) {
        ConstructionMaterialFlyweight material = materials.get(materialType);
        
        if (material == null) {
            switch (materialType) {
                case "concrete":
                    material = new ConstructionMaterialFlyweight(
                        "concrete", "Standard Mix PSI 4000", "ConcreteSupplier Inc.");
                    break;
                case "steel":
                    material = new ConstructionMaterialFlyweight(
                        "steel", "Grade 60 Rebar", "SteelWorks Co.");
                    break;
                case "wood":
                    material = new ConstructionMaterialFlyweight(
                        "wood", "Pressure Treated Lumber", "Lumber Mill Ltd.");
                    break;
                default:
                    throw new IllegalArgumentException("Unknown material type: " + materialType);
            }
            materials.put(materialType, material);
        }
        
        return material;
    }
    
    public static int getMaterialTypesCount() {
        return materials.size();
    }
}

// Client class demonstrating usage
public class ConstructionSite {
    private final List<Pair<ConstructionMaterialFlyweight, MaterialContext>> usedMaterials = new ArrayList<>();
    
    public void useMaterial(String materialType, String location, int quantity) {
        ConstructionMaterialFlyweight material = ConstructionMaterialFactory.getMaterial(materialType);
        MaterialContext context = new MaterialContext(location, quantity);
        material.use(context);
        usedMaterials.add(new Pair<>(material, context));
    }
    
    public int getTotalMaterialTypes() {
        return ConstructionMaterialFactory.getMaterialTypesCount();
    }
}
