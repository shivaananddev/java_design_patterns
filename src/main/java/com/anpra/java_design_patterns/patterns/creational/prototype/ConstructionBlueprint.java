package com.anpra.java_design_patterns.patterns.creational.prototype;

/**
 * 🏗️ Analogy: Construction Blueprint
 * Creating copies of a blueprint for different houses
 */
public abstract class ConstructionBlueprint implements Cloneable {
    private String projectName;
    private double squareFootage;
    private List<String> specifications;

    public ConstructionBlueprint() {
        specifications = new ArrayList<>();
    }

    public ConstructionBlueprint(ConstructionBlueprint blueprint) {
        this.projectName = blueprint.projectName;
        this.squareFootage = blueprint.squareFootage;
        this.specifications = new ArrayList<>(blueprint.specifications);
    }

    public abstract ConstructionBlueprint clone();

    // Getters and Setters
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public double getSquareFootage() { return squareFootage; }
    public void setSquareFootage(double squareFootage) { this.squareFootage = squareFootage; }
    public List<String> getSpecifications() { return new ArrayList<>(specifications); }
    public void addSpecification(String specification) { specifications.add(specification); }
}

public class ResidentialBlueprint extends ConstructionBlueprint {
    private int numberOfBedrooms;
    private int numberOfBathrooms;

    public ResidentialBlueprint() {
        super();
    }

    public ResidentialBlueprint(ResidentialBlueprint blueprint) {
        super(blueprint);
        this.numberOfBedrooms = blueprint.numberOfBedrooms;
        this.numberOfBathrooms = blueprint.numberOfBathrooms;
    }

    @Override
    public ConstructionBlueprint clone() {
        return new ResidentialBlueprint(this);
    }

    // Getters and Setters
    public int getNumberOfBedrooms() { return numberOfBedrooms; }
    public void setNumberOfBedrooms(int numberOfBedrooms) { this.numberOfBedrooms = numberOfBedrooms; }
    public int getNumberOfBathrooms() { return numberOfBathrooms; }
    public void setNumberOfBathrooms(int numberOfBathrooms) { this.numberOfBathrooms = numberOfBathrooms; }
}

public class CommercialBlueprint extends ConstructionBlueprint {
    private int numberOfFloors;
    private boolean hasElevator;

    public CommercialBlueprint() {
        super();
    }

    public CommercialBlueprint(CommercialBlueprint blueprint) {
        super(blueprint);
        this.numberOfFloors = blueprint.numberOfFloors;
        this.hasElevator = blueprint.hasElevator;
    }

    @Override
    public ConstructionBlueprint clone() {
        return new CommercialBlueprint(this);
    }

    // Getters and Setters
    public int getNumberOfFloors() { return numberOfFloors; }
    public void setNumberOfFloors(int numberOfFloors) { this.numberOfFloors = numberOfFloors; }
    public boolean hasElevator() { return hasElevator; }
    public void setHasElevator(boolean hasElevator) { this.hasElevator = hasElevator; }
}
