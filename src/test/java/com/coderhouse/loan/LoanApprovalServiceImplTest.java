package com.coderhouse.loan;

import com.coderhouse.exception.ApprovalDeniedException;
import com.coderhouse.tax.TaxPerTypeClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LoanApprovalServiceImplTest {

    @InjectMocks
    private LoanApprovalServiceImpl loanApprovalServiceImpl;

    @Mock
    private TaxPerTypeClient taxPerClient;

    @Test
    public void testLoanApprovalService_clientTypeA_IsApproved() throws ApprovalDeniedException {
        when(taxPerClient.typeA()).thenReturn(1.2);
        assertEquals(12, loanApprovalServiceImpl.evaluate(10, 20, "A"),0);
    }

    @Test
    public void testLoanApprovalService_clientTypeB_IsApproved() throws ApprovalDeniedException {
        when(taxPerClient.typeB()).thenReturn(1.3);
        assertEquals(13, loanApprovalServiceImpl.evaluate(10, 20, "B"),0);
    }

    @Test
    public void testLoanApprovalService_clientTypeOther_IsApproved() throws ApprovalDeniedException {
        when(taxPerClient.other()).thenReturn(1.4);
        assertEquals(14, loanApprovalServiceImpl.evaluate(10, 20, "C"),0);
    }

    @Test
    public void testLoanApprovalService_clientTypeA_IsDenied() {
        when(taxPerClient.typeA()).thenReturn(1.2);

        ApprovalDeniedException exception = assertThrows(ApprovalDeniedException.class, () -> {
            loanApprovalServiceImpl.evaluate(10, 10, "A");
        });

        assertEquals("Capacity loan is not sufficient to get a new loan", exception.getMessage());

    }
}