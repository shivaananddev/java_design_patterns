package com.anpra.java_design_patterns.patterns.behavioral.chainofresponsibility;

/**
 * Executive Board handler - handles very large or critical projects
 */
public class ExecutiveBoardHandler extends BaseApprovalHandler {
    private static final double COST_LIMIT = Double.MAX_VALUE;
    private static final int COMPLEXITY_LIMIT = 10;

    public ExecutiveBoardHandler() {
        super("Executive Board", COST_LIMIT, COMPLEXITY_LIMIT);
    }

    @Override
    public void processRequest(ConstructionRequest request) {
        if (canHandle(request)) {
            // Executive board requires special documentation for very large projects
            if (request.getEstimatedCost() > 5000000.0) {
                logDecision(request, "PENDING SPECIAL REVIEW");
                // In real implementation, this would trigger a special review process
            } else {
                logDecision(request, "APPROVED");
            }
        } else {
            // This should never happen as Executive Board has no limits
            logDecision(request, "REJECTED - EXCEEDS ALL AUTHORITY LEVELS");
        }
    }
}
