package com.anpra.java_design_patterns.patterns.behavioral.chainofresponsibility;

/**
 * Handler interface for construction approvals
 */
public interface ApprovalHandler {
    void setNextHandler(ApprovalHandler handler);
    void processRequest(ConstructionRequest request);
}
