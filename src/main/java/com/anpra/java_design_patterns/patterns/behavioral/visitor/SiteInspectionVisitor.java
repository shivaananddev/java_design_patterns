package com.anpra.java_design_patterns.patterns.behavioral.visitor;

/**
 * Visitor interface for construction site inspections
 */
public interface SiteInspectionVisitor {
    void visitStructuralElement(StructuralElement element);
    void visitElectricalSystem(ElectricalSystem system);
    void visitPlumbingSystem(PlumbingSystem system);
    void visitSafetySystem(SafetySystem system);
    
    InspectionReport getReport();
}
