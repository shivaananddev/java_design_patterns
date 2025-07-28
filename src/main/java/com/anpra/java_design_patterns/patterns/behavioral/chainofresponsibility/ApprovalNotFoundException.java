package com.anpra.java_design_patterns.patterns.behavioral.chainofresponsibility;

/**
 * Custom exception for approval chain failures
 */
public class ApprovalNotFoundException extends RuntimeException {
    public ApprovalNotFoundException(String message) {
        super(message);
    }
}
