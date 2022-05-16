package com.coderhouse.loan;

import com.coderhouse.exception.ApprovalDeniedException;

public interface LoanApprovalService {

    double evaluate(double amountLoan, double capacityLoan, String typeClient) throws ApprovalDeniedException;
}
