import org.example.Assignment.FilterInvoice;
import org.example.Assignment.Invoice;
import org.example.Assignment.QueryInvoicesDAO;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class filterInvoicesTest {
    @Test
    public void filterInvoiceStubbedTest() {
        //I first create an instance of filter invoice
        FilterInvoice filterInvoice = new FilterInvoice();
        //Then I mock the DAO
        QueryInvoicesDAO mockDao = mock(QueryInvoicesDAO.class);
        //I stub a list of invoices and customer, one is less than 100 and the other is greater than 100
        //I use two lists so I can confirm that lowValueInvoices filters the correct case
        when(mockDao.all()).thenReturn(Arrays.asList(new Invoice("1", 20), new Invoice("2", 120)));
        //by using my setter injection I seperate the test from the real database
        filterInvoice.setDao(mockDao);
        //I call lowValueInvoices
        List<Invoice> lowValueInvoices = filterInvoice.lowValueInvoices();
        // I assert the correct size of the list and the correct value that lowValueInvoices returns
        assertEquals(1, lowValueInvoices.size());
        assertEquals(20, lowValueInvoices.get(0).getValue());
    }
}
