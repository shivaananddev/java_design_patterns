package com.anpra.java_design_patterns.patterns.behavioral.visitor;

/**
 * Interface for visitable construction elements
 */
public interface SiteElement {
    void accept(SiteInspectionVisitor visitor);
    String getLocation();
    String getElementId();
}
