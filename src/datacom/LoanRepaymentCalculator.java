package datacom;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Calculates the repayments on a reducing loan (non-table loan) given the
 * following inputs:
 *   The amount, greater than zero expressed as $ddddd.nn where the number of dollars may vary and the number of cents is optional
 *   The number of periods, expressed as a simple integer greater than zero e.g. 12
 *   The frequency of repayments, either weekly, fortnightly or monthly
 *   The interest rate, expressed as a percentage e.g. 5.25% greater than zero
 *   The loan start date, expressed as YYYY-MM-DD
 *
 * Interest is to be calculated daily and repayment amounts rounded up to the nearest cent.
 * Where the loan amount cannot be divided equally the last principal amount should be larger than the other payments
 * (e.g. 100 / 3 months = 33.33, 33.33 & 33.34 principal).
 *
 * The results should be formatted as $ddd.cc with as many dollars as required
 *
 * @author simonda
 *
 */
public class LoanRepaymentCalculator {
    public List<String> calculateRepayments(String amount, String numberOfPeriods, String frequency, String interestRate,
                                            String loanStartDate) throws Exception {
        try {
            double amountValue = Double.parseDouble(amount.trim().replace("$", ""));
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date startingDate = sdf.parse(loanStartDate);
            Date nextRepaymentDateForMonthly = startingDate;
            Calendar nextRepaymentDateForMonthlyCal = Calendar.getInstance();
            nextRepaymentDateForMonthlyCal.setTime(nextRepaymentDateForMonthly);
            nextRepaymentDateForMonthlyCal.set(Calendar.DATE, 1);
            int noOfInstalments = Integer.parseInt(numberOfPeriods);
            BigDecimal interestDecimal = new BigDecimal(interestRate.trim().replace("%", "")).divide(BigDecimal.valueOf(100));
            List<String> repaymentList = new ArrayList<>();
            double amountPerRepayment = LoanRepaymentCalculator.roundToTwoDecimalPoints((amountValue / noOfInstalments));
            double remainder = amountValue - amountPerRepayment * noOfInstalments;
            double remainingCapital = amountValue;
            for (int i = 0; i < noOfInstalments; i++) {
                if (i == noOfInstalments - 1) {
                    amountPerRepayment += remainder;
                }
                double interest = calculateInterestForRepayment(frequency, remainingCapital, interestDecimal.doubleValue(), nextRepaymentDateForMonthlyCal);
                double repaymentAmount = amountPerRepayment + interest;
                repaymentList.add("$" + String.format("%.2f", repaymentAmount));
                remainingCapital -= amountPerRepayment;
                nextRepaymentDateForMonthlyCal.add(Calendar.MONTH, 1);
            }
            return repaymentList;
        } catch (Exception e) {
            throw new Exception("Invalid Input Values");
        }
    }

    public static double roundToTwoDecimalPoints(double value) {
        BigDecimal bigDecimal = BigDecimal.valueOf(value);
        bigDecimal = bigDecimal.setScale(2);
        return bigDecimal.doubleValue();
    }

    public static double calculateInterestForRepayment(String frequency, double repaymentCapital, double interestRate, Calendar nextRepaymentDateForMonthlyCal) {
        double interest = 0;
        double dailyInterest = (repaymentCapital * interestRate) / 365;
        switch(frequency.toLowerCase()) {
            case "weekly":
                interest = dailyInterest * 7;
                break;
            case "fortnightly":
                interest = dailyInterest * 14;
                break;
            case "monthly":
                interest = dailyInterest * nextRepaymentDateForMonthlyCal.getActualMaximum(Calendar.DAY_OF_MONTH);
                break;
            default:
                interest = -1;
        }
        return roundToTwoDecimalPoints(interest);
    }

    public static void main(String[] args) throws Exception {
        double input = 3;
        System.out.println("double : " + input);
        System.out.println("double : " + String.format("%.2f", input));
        System.out.format("double : %.2f", input);
        LoanRepaymentCalculator lrc = new LoanRepaymentCalculator();
        List<String> repayments = lrc.calculateRepayments("$10000.00", "10", "monthly", "365%", "2019-01-01");
        repayments = lrc.calculateRepayments("$10000.00", "3", "weekly", "365%", "2019-01-01");
        repayments = lrc.calculateRepayments("$10000.00", "10", "monthly", "366%", "2020-01-01");
    }
}
