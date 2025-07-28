package com.anpra.java_design_patterns.patterns.structural.facade;

/**
 * 🏗️ Analogy: Construction Project Management Office
 * Like a PMO that simplifies complex construction processes for clients
 */
public class ConstructionFacade {
    private final PermitService permitService;
    private final ArchitecturalService architecturalService;
    private final ContractorService contractorService;
    private final InspectionService inspectionService;
    
    public ConstructionFacade() {
        this.permitService = new PermitService();
        this.architecturalService = new ArchitecturalService();
        this.contractorService = new ContractorService();
        this.inspectionService = new InspectionService();
    }
    
    // Simple interface for client to build a house
    public void constructBuilding(String buildingType) {
        System.out.println("Starting construction process for: " + buildingType);
        
        permitService.applyForPermit(buildingType);
        architecturalService.createBlueprints(buildingType);
        contractorService.hireContractors();
        
        contractorService.startConstruction();
        inspectionService.scheduleInspection();
        
        if (inspectionService.conductInspection()) {
            System.out.println("Construction completed successfully!");
        }
    }
}

// Complex subsystems
class PermitService {
    public void applyForPermit(String buildingType) {
        System.out.println("Applying for construction permits for " + buildingType);
        validateZoning();
        submitDocuments();
    }
    
    private void validateZoning() {
        System.out.println("Validating zoning requirements");
    }
    
    private void submitDocuments() {
        System.out.println("Submitting permit documents");
    }
}

class ArchitecturalService {
    public void createBlueprints(String buildingType) {
        System.out.println("Creating architectural blueprints");
        designFoundation(buildingType);
        designStructure();
    }
    
    private void designFoundation(String buildingType) {
        System.out.println("Designing foundation for " + buildingType);
    }
    
    private void designStructure() {
        System.out.println("Designing main structure");
    }
}

class ContractorService {
    private boolean contractorsHired = false;
    
    public void hireContractors() {
        System.out.println("Hiring general and specialized contractors");
        contractorsHired = true;
    }
    
    public void startConstruction() {
        if (!contractorsHired) {
            throw new IllegalStateException("Must hire contractors before starting construction");
        }
        System.out.println("Starting construction work");
        prepareFoundation();
        buildStructure();
        completeFinishing();
    }
    
    private void prepareFoundation() {
        System.out.println("Preparing and pouring foundation");
    }
    
    private void buildStructure() {
        System.out.println("Building main structure");
    }
    
    private void completeFinishing() {
        System.out.println("Completing finishing work");
    }
}

class InspectionService {
    public void scheduleInspection() {
        System.out.println("Scheduling final inspection");
    }
    
    public boolean conductInspection() {
        System.out.println("Conducting final inspection");
        return checkStructuralIntegrity() && 
               checkSafetySystems() && 
               checkUtilities();
    }
    
    private boolean checkStructuralIntegrity() {
        System.out.println("Checking structural integrity");
        return true;
    }
    
    private boolean checkSafetySystems() {
        System.out.println("Checking safety systems");
        return true;
    }
    
    private boolean checkUtilities() {
        System.out.println("Checking utilities");
        return true;
    }
}
