package com.anpra.java_design_patterns.patterns.behavioral.chainofresponsibility;

import java.util.logging.Logger;

/**
 * Abstract base handler implementing common chain functionality
 */
public abstract class BaseApprovalHandler implements ApprovalHandler {
    protected static final Logger LOGGER = Logger.getLogger(BaseApprovalHandler.class.getName());
    protected ApprovalHandler nextHandler;
    protected final String handlerName;
    protected final double costLimit;
    protected final int complexityLimit;

    protected BaseApprovalHandler(String handlerName, double costLimit, int complexityLimit) {
        this.handlerName = handlerName;
        this.costLimit = costLimit;
        this.complexityLimit = complexityLimit;
    }

    @Override
    public void setNextHandler(ApprovalHandler handler) {
        this.nextHandler = handler;
    }

    protected void logDecision(ConstructionRequest request, String decision) {
        LOGGER.info(String.format("[%s] %s for Project: %s (Cost: $%.2f, Complexity: %d)",
            handlerName, decision, request.getProjectId(), 
            request.getEstimatedCost(), request.getComplexityLevel()));
    }

    protected boolean canHandle(ConstructionRequest request) {
        return request.getEstimatedCost() <= costLimit && 
               request.getComplexityLevel() <= complexityLimit;
    }

    protected void forwardToNextHandler(ConstructionRequest request) {
        if (nextHandler != null) {
            nextHandler.processRequest(request);
        } else {
            LOGGER.warning("End of chain reached without approval for project: " + request.getProjectId());
            throw new ApprovalNotFoundException("No suitable approver found for the request");
        }
    }
}
