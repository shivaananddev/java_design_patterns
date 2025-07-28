package com.anpra.java_design_patterns.patterns.behavioral.chainofresponsibility;

/**
 * Site Supervisor handler - handles small projects
 */
public class SiteSupervisorHandler extends BaseApprovalHandler {
    private static final double COST_LIMIT = 50000.0;
    private static final int COMPLEXITY_LIMIT = 3;

    public SiteSupervisorHandler() {
        super("Site Supervisor", COST_LIMIT, COMPLEXITY_LIMIT);
    }

    @Override
    public void processRequest(ConstructionRequest request) {
        if (canHandle(request) && !request.requiresPermits()) {
            logDecision(request, "APPROVED");
        } else {
            logDecision(request, "FORWARDED");
            forwardToNextHandler(request);
        }
    }
}
