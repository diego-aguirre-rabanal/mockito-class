package com.coderhouse.loan;

import com.coderhouse.exception.ApprovalDeniedException;
import com.coderhouse.tax.TaxPerTypeClient;

public class LoanApprovalServiceImpl implements LoanApprovalService {

    private TaxPerTypeClient taxPerClient;

    public LoanApprovalServiceImpl(TaxPerTypeClient taxPerTypeClient) {
        this.taxPerClient = taxPerTypeClient;
    }

    @Override
    public double evaluate(double amountLoan, double capacityLoan, String typeClient) throws ApprovalDeniedException {
        double tax = 0.0;
        switch (typeClient) {
            case "A": {
                tax = taxPerClient.typeA();
                break;
            }
            case "B": {
                tax = taxPerClient.typeB();
                break;
            }
            default : {
                tax =  taxPerClient.other();
                break;
            }
        }
        double loanPlusTaxAmount = amountLoan * tax;
        if(loanPlusTaxAmount > capacityLoan){
            throw new ApprovalDeniedException("Capacity loan is not sufficient to get a new loan");
        }else{
            return loanPlusTaxAmount;
        }
    }
}
