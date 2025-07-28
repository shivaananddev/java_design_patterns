package com.anpra.java_design_patterns.patterns.structural.bridge;

/**
 * 🏗️ Analogy: Construction Project Management
 * Separates project types (residential, commercial) from project management methods (agile, waterfall)
 */
public interface ProjectManagementMethod {
    void planProject();
    void executeProject();
    void monitorProgress();
}

// Implementation hierarchy
public class AgileManagement implements ProjectManagementMethod {
    @Override
    public void planProject() {
        System.out.println("Planning project using Agile sprints");
    }
    
    @Override
    public void executeProject() {
        System.out.println("Executing project with daily stand-ups");
    }
    
    @Override
    public void monitorProgress() {
        System.out.println("Monitoring through sprint burndown charts");
    }
}

public class WaterfallManagement implements ProjectManagementMethod {
    @Override
    public void planProject() {
        System.out.println("Creating comprehensive upfront project plan");
    }
    
    @Override
    public void executeProject() {
        System.out.println("Executing project in sequential phases");
    }
    
    @Override
    public void monitorProgress() {
        System.out.println("Monitoring through milestone completion");
    }
}

// Abstraction hierarchy
public abstract class ConstructionProject {
    protected ProjectManagementMethod managementMethod;
    
    protected ConstructionProject(ProjectManagementMethod method) {
        this.managementMethod = method;
    }
    
    public abstract void startProject();
    public abstract void trackProgress();
}

public class ResidentialProject extends ConstructionProject {
    public ResidentialProject(ProjectManagementMethod method) {
        super(method);
    }
    
    @Override
    public void startProject() {
        System.out.println("Starting residential project:");
        managementMethod.planProject();
        managementMethod.executeProject();
    }
    
    @Override
    public void trackProgress() {
        System.out.println("Tracking residential project:");
        managementMethod.monitorProgress();
    }
}

public class CommercialProject extends ConstructionProject {
    public CommercialProject(ProjectManagementMethod method) {
        super(method);
    }
    
    @Override
    public void startProject() {
        System.out.println("Starting commercial project:");
        managementMethod.planProject();
        managementMethod.executeProject();
    }
    
    @Override
    public void trackProgress() {
        System.out.println("Tracking commercial project:");
        managementMethod.monitorProgress();
    }
}
