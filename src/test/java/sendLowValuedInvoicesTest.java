import org.example.Assignment.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class sendLowValuedInvoicesTest {
    @Test
    public void testWhenLowInvoicesSent() {
        // First I create a mock for both filter and sap
        FilterInvoice mockFilter = mock(FilterInvoice.class);
        SAP mockSap = mock(SAP.class);
        //create a low valued invoice to use
        Invoice lowValueInvoice = new Invoice("1", 20);
        // I stub a list calling the lowValueInvoices to return a list with my created invoice
        //I call the lowValueInvoices, so I can follow the way the program works and the method
        //takes in that lowValueInvoices list, so I create one through the test
        when(mockFilter.lowValueInvoices()).thenReturn(Arrays.asList(lowValueInvoice));
        //I use my mocks for SAP_BasedInvoiceSender, so I can call on the method
        SAP_BasedInvoiceSender sapsender = new SAP_BasedInvoiceSender(mockFilter, mockSap);
        // Call the method with my mocks
        sapsender.sendLowValuedInvoices();
        // then I verify that sap.send() has occurred
        verify(mockSap).send(lowValueInvoice);
    }
    @Test
    public void testWhenNoInvoices() {
        // First I create a mock for both filter and sap
        FilterInvoice mockFilter = mock(FilterInvoice.class);
        SAP mockSap = mock(SAP.class);
        // I stub the call to lowValueInvoices using an empty list
        //When I tried to create an empty invoice, I realized it still counts as an invoice so an empty list
        //is best for declaring that there are no invoices.
        when(mockFilter.lowValueInvoices()).thenReturn(Collections.emptyList());
        // I use my mocks for SAP_BasedInvoiceSender, so I can call on the method
        SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(mockFilter, mockSap);
        // Call the method with my mocks
        sender.sendLowValuedInvoices();
        // then I verify that sap.send never occurred
        verify(mockSap, never()).send(any(Invoice.class));
    }

}
