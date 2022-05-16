package com.coderhouse.exception;

public class ApprovalDeniedException extends RuntimeException {
    public ApprovalDeniedException(String message) {
        super(message);
    }
}
