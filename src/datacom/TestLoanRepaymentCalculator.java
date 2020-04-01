package datacom;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

/**
 * Created by danushka on 3/5/20.
 */

public class TestLoanRepaymentCalculator {

    @Test
    public void testCalculateRepayments() throws Exception {
        LoanRepaymentCalculator lrc = new LoanRepaymentCalculator();
        List<String> repayments = lrc.calculateRepayments("$10000.00", "10", "monthly", "365%", "2019-01-01");

        assertEquals(10, repayments.size());
        assertEquals("$4100.00", repayments.get(0));
        assertEquals("$3520.00", repayments.get(1));
        assertEquals("$3480.00", repayments.get(2));
        assertEquals("$3100.00", repayments.get(3));
        assertEquals("$2860.00", repayments.get(4));
        assertEquals("$2500.00", repayments.get(5));
        assertEquals("$2240.00", repayments.get(6));
        assertEquals("$1930.00", repayments.get(7));
        assertEquals("$1600.00", repayments.get(8));
        assertEquals("$1310.00", repayments.get(9));
    }

    @Test
    public void testCalculateRepaymentsRounding() throws Exception {
        LoanRepaymentCalculator lrc = new LoanRepaymentCalculator();
        List<String> repayments = lrc.calculateRepayments("$10000.00", "3", "weekly", "365%", "2019-01-01");

        assertEquals(3, repayments.size());
        assertEquals("$4033.33", repayments.get(0));
        assertEquals("$3800.00", repayments.get(1));
        assertEquals("$3566.68", repayments.get(2));
    }

    @Test
    public void testCalculateRepaymentsLeapYear() throws Exception {
        LoanRepaymentCalculator lrc = new LoanRepaymentCalculator();
        List<String> repayments = lrc.calculateRepayments("$10000.00", "10", "monthly", "366%", "2020-01-01");

        assertEquals(10, repayments.size());
        assertEquals("$4100.00", repayments.get(0));
        assertEquals("$3610.00", repayments.get(1));
        assertEquals("$3480.00", repayments.get(2));
        assertEquals("$3100.00", repayments.get(3));
        assertEquals("$2860.00", repayments.get(4));
        assertEquals("$2500.00", repayments.get(5));
        assertEquals("$2240.00", repayments.get(6));
        assertEquals("$1930.00", repayments.get(7));
        assertEquals("$1600.00", repayments.get(8));
        assertEquals("$1310.00", repayments.get(9));
    }

    @Test
    public void testInvalidParameters() {
        testInvalidParameters("10", "10", "monthly", "2.56%", "2019-01-01");
        testInvalidParameters("$10000", "-5", "monthly", "2.56%", "2019-01-01");
        testInvalidParameters("$10000", "abc", "monthly", "2.56%", "2019-01-01");
        testInvalidParameters("$10000", "10", "abc", "2.56%", "2019-01-01");
        testInvalidParameters("$10000", "10", "monthly", "2.56", "2019-01-01");
        testInvalidParameters("$10000", "10", "monthly", "2.56%", "abc");
        testInvalidParameters("$10000", "10", "monthly", "2.56%", "1/7/2019");
    }

    private void testInvalidParameters(String amount, String numberOfPeriods, String frequency, String interestRate,
                                       String loanStartDate) {
        try {
            LoanRepaymentCalculator lrc = new LoanRepaymentCalculator();
            lrc.calculateRepayments(amount, numberOfPeriods, frequency, interestRate, loanStartDate);

            fail("Exception not thrown");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // feel free to add any other tests you desire
}
