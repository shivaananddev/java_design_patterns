package com.anpra.java_design_patterns.patterns.creational.factory;

/**
 * 🏗️ Analogy: Construction Equipment Factory
 * Different types of construction equipment are created based on the requirement
 */
public interface ConstructionEquipment {
    void operate();
    void maintain();
    String getEquipmentType();
}

public class Crane implements ConstructionEquipment {
    private final String model;
    private final int liftingCapacity;

    public Crane(String model, int liftingCapacity) {
        this.model = model;
        this.liftingCapacity = liftingCapacity;
    }

    @Override
    public void operate() {
        System.out.println("Operating crane: " + model);
    }

    @Override
    public void maintain() {
        System.out.println("Maintaining crane: " + model);
    }

    @Override
    public String getEquipmentType() {
        return "Crane";
    }
}

public class Bulldozer implements ConstructionEquipment {
    private final String model;
    private final String engineType;

    public Bulldozer(String model, String engineType) {
        this.model = model;
        this.engineType = engineType;
    }

    @Override
    public void operate() {
        System.out.println("Operating bulldozer: " + model);
    }

    @Override
    public void maintain() {
        System.out.println("Maintaining bulldozer: " + model);
    }

    @Override
    public String getEquipmentType() {
        return "Bulldozer";
    }
}

public class ConstructionEquipmentFactory {
    public static ConstructionEquipment createEquipment(String type, String model) {
        return switch (type.toLowerCase()) {
            case "crane" -> new Crane(model, 5000);
            case "bulldozer" -> new Bulldozer(model, "Diesel");
            default -> throw new IllegalArgumentException("Unknown equipment type: " + type);
        };
    }
}
