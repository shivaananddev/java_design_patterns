package com.anpra.java_design_patterns.patterns.behavioral.chainofresponsibility;

/**
 * Project Manager handler - handles medium-sized projects
 */
public class ProjectManagerHandler extends BaseApprovalHandler {
    private static final double COST_LIMIT = 200000.0;
    private static final int COMPLEXITY_LIMIT = 6;

    public ProjectManagerHandler() {
        super("Project Manager", COST_LIMIT, COMPLEXITY_LIMIT);
    }

    @Override
    public void processRequest(ConstructionRequest request) {
        if (canHandle(request)) {
            if (request.requiresPermits()) {
                // Can handle with permits up to medium complexity
                if (request.getComplexityLevel() <= 4) {
                    logDecision(request, "APPROVED");
                    return;
                }
            } else {
                logDecision(request, "APPROVED");
                return;
            }
        }
        logDecision(request, "FORWARDED");
        forwardToNextHandler(request);
    }
}
