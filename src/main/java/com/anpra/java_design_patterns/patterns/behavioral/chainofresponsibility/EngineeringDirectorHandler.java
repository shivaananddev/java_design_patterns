package com.anpra.java_design_patterns.patterns.behavioral.chainofresponsibility;

/**
 * Engineering Director handler - handles large complex projects
 */
public class EngineeringDirectorHandler extends BaseApprovalHandler {
    private static final double COST_LIMIT = 1000000.0;
    private static final int COMPLEXITY_LIMIT = 8;

    public EngineeringDirectorHandler() {
        super("Engineering Director", COST_LIMIT, COMPLEXITY_LIMIT);
    }

    @Override
    public void processRequest(ConstructionRequest request) {
        if (canHandle(request)) {
            logDecision(request, "APPROVED");
        } else {
            logDecision(request, "FORWARDED");
            forwardToNextHandler(request);
        }
    }
}
