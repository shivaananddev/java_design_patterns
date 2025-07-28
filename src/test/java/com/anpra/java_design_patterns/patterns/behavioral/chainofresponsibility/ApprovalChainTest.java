package com.anpra.java_design_patterns.patterns.behavioral.chainofresponsibility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

class ApprovalChainTest {
    private ApprovalChain approvalChain;

    @BeforeEach
    void setUp() {
        approvalChain = new ApprovalChain();
    }

    @Test
    void testSupervisorApproval() {
        ChangeRequest request = new ChangeRequest(
            "Minor wall repair",
            "Maintenance",
            new BigDecimal("3000"),
            1
        );

        ApprovalResponse response = approvalChain.processRequest(request);

        assertTrue(response.isApproved());
        assertEquals("Supervisor", response.getApproverRole());
    }

    @Test
    void testProjectManagerApproval() {
        ChangeRequest request = new ChangeRequest(
            "Additional room",
            "Extension",
            new BigDecimal("20000"),
            4
        );

        ApprovalResponse response = approvalChain.processRequest(request);

        assertTrue(response.isApproved());
        assertEquals("Project Manager", response.getApproverRole());
    }

    @Test
    void testExecutiveBoardApproval() {
        ChangeRequest request = new ChangeRequest(
            "New wing construction",
            "Major Change",
            new BigDecimal("90000"),
            10
        );

        ApprovalResponse response = approvalChain.processRequest(request);

        assertTrue(response.isApproved());
        assertEquals("Executive Board", response.getApproverRole());
    }

    @Test
    void testRequestRejection() {
        ChangeRequest request = new ChangeRequest(
            "Complete rebuild",
            "Major Change",
            new BigDecimal("150000"),
            20
        );

        ApprovalResponse response = approvalChain.processRequest(request);

        assertFalse(response.isApproved());
        assertEquals("Executive Board", response.getApproverRole());
    }
}
