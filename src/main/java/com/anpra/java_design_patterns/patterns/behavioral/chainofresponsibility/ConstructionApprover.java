package com.anpra.java_design_patterns.patterns.behavioral.chainofresponsibility;

import java.math.BigDecimal;

/**
 * 🏗️ Analogy: Construction Approval Chain
 * Like a hierarchical approval process for construction changes, where each level
 * can either approve the request or pass it up the chain
 */
public abstract class ConstructionApprover {
    protected ConstructionApprover nextApprover;
    protected String role;
    protected BigDecimal approvalLimit;

    protected ConstructionApprover(String role, BigDecimal approvalLimit) {
        this.role = role;
        this.approvalLimit = approvalLimit;
    }

    public void setNextApprover(ConstructionApprover nextApprover) {
        this.nextApprover = nextApprover;
    }

    public abstract ApprovalResponse approveRequest(ChangeRequest request);
}

public class ChangeRequest {
    private final String description;
    private final String type;
    private final BigDecimal cost;
    private final int timeImpact;

    public ChangeRequest(String description, String type, BigDecimal cost, int timeImpact) {
        this.description = description;
        this.type = type;
        this.cost = cost;
        this.timeImpact = timeImpact;
    }

    // Getters
    public String getDescription() { return description; }
    public String getType() { return type; }
    public BigDecimal getCost() { return cost; }
    public int getTimeImpact() { return timeImpact; }
}

public class ApprovalResponse {
    private final boolean approved;
    private final String approverRole;
    private final String reason;

    public ApprovalResponse(boolean approved, String approverRole, String reason) {
        this.approved = approved;
        this.approverRole = approverRole;
        this.reason = reason;
    }

    // Getters
    public boolean isApproved() { return approved; }
    public String getApproverRole() { return approverRole; }
    public String getReason() { return reason; }
}

public class Supervisor extends ConstructionApprover {
    public Supervisor() {
        super("Supervisor", new BigDecimal("5000"));
    }

    @Override
    public ApprovalResponse approveRequest(ChangeRequest request) {
        if (request.getCost().compareTo(approvalLimit) <= 0 && request.getTimeImpact() <= 2) {
            return new ApprovalResponse(true, role, 
                "Change approved by Supervisor - within cost and time limits");
        }
        
        if (nextApprover != null) {
            return nextApprover.approveRequest(request);
        }
        
        return new ApprovalResponse(false, role, 
            "Change rejected - beyond Supervisor's authority and no higher approver available");
    }
}

public class ProjectManager extends ConstructionApprover {
    public ProjectManager() {
        super("Project Manager", new BigDecimal("25000"));
    }

    @Override
    public ApprovalResponse approveRequest(ChangeRequest request) {
        if (request.getCost().compareTo(approvalLimit) <= 0 && request.getTimeImpact() <= 5) {
            return new ApprovalResponse(true, role, 
                "Change approved by Project Manager - within cost and time limits");
        }
        
        if (nextApprover != null) {
            return nextApprover.approveRequest(request);
        }
        
        return new ApprovalResponse(false, role, 
            "Change rejected - beyond Project Manager's authority and no higher approver available");
    }
}

public class ExecutiveBoard extends ConstructionApprover {
    public ExecutiveBoard() {
        super("Executive Board", new BigDecimal("100000"));
    }

    @Override
    public ApprovalResponse approveRequest(ChangeRequest request) {
        if (request.getCost().compareTo(approvalLimit) <= 0) {
            return new ApprovalResponse(true, role, 
                "Change approved by Executive Board - within cost limit");
        }
        
        return new ApprovalResponse(false, role, 
            "Change rejected - cost exceeds maximum approval limit");
    }
}

public class ApprovalChain {
    private final ConstructionApprover chain;

    public ApprovalChain() {
        // Build the chain
        Supervisor supervisor = new Supervisor();
        ProjectManager projectManager = new ProjectManager();
        ExecutiveBoard executiveBoard = new ExecutiveBoard();

        supervisor.setNextApprover(projectManager);
        projectManager.setNextApprover(executiveBoard);

        this.chain = supervisor;
    }

    public ApprovalResponse processRequest(ChangeRequest request) {
        return chain.approveRequest(request);
    }
}
