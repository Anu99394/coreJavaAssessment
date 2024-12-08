import java.util.*;

public class Main {
    // Function to calculate EMI for home loan
    public static double calculateEMI(double principal, double annualRate, int tenureYears) {
        double r = annualRate / 12 / 100;
        int n = tenureYears * 12;
        return (principal * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
    }

    // Function to calculate the  home value after appreciation for the each year
    public static double calculateHomeValue(double initialValue, double salvageValue, double appreciationRate, int years) {
        double depreciationRate = (initialValue - salvageValue) / years;
        System.out.println("Year\tHome Value\tAppreciation Value\tDepreciation Value");
        for (int year = 1; year <= years; year++) {
            double appreciationValue = initialValue * Math.pow(1 + appreciationRate, year);
            double depreciationValue = depreciationRate * year;
            double homeValue = appreciationValue - depreciationValue;
            System.out.printf("%d\t%.2f\t\t%.2f\t\t\t%.2f\n", year, homeValue, appreciationValue, depreciationValue);
        }
//        double depreciationRate=(initialValue-salvageValue)/years;
        double value = initialValue * Math.pow(1 + appreciationRate,years) - (years* depreciationRate);
        return value;
    }

    // Function to calculate SIP returns foe the given tenure 
    public static double calculateSIPValue(double monthlyContribution, double annualReturn, int years) {
        double monthlyReturn = annualReturn / 12 / 100;
        double totalMoneyMade = 0;
        System.out.println("Year\tSIP Value Made");
        for (int year = 1; year <= years; year++) {
            int totalMonths = year * 12;
            double maturityAmount = monthlyContribution * (Math.pow(1 + monthlyReturn, totalMonths) - 1) / monthlyReturn * (1 + monthlyReturn);
            System.out.printf("%d\t%.2f\n", year, maturityAmount);
            totalMoneyMade = maturityAmount;
        }
        return totalMoneyMade;
    }

    // Function to generate the annual amortization
    public static void generateAnnualAmortization(double principal, double annualRate, int tenureYears) {
        double emi = calculateEMI(principal, annualRate, tenureYears);
        double balance = principal;
        double monthlyRate = annualRate / 12 / 100;

        System.out.println("Annual Amortization:");
        System.out.println("Year | Total EMI Paid | Principal Paid | Interest Paid | Remaining Balance");

        for (int year = 1; year <= tenureYears; year++) {
            double annualPrincipalPaid = 0;
            double annualInterestPaid = 0;
            
            for (int month = 1; month <= 12; month++) {
                double interest = balance * monthlyRate;
                double principalPayment = emi - interest;
                annualPrincipalPaid += principalPayment;
                annualInterestPaid += interest;
                balance -= principalPayment;
            }
            
            System.out.printf("%-4d | ₹%.2f         | ₹%.2f         | ₹%.2f        | ₹%.2f%n",
                    year, emi * 12, annualPrincipalPaid, annualInterestPaid, balance);
        }
    }
    
    public static void investmentComparison(double homePrice, double downPayment, double loanRate, int tenureYears,
                                            double salvageValue, double appreciationRate, double sipMonthlyContribution,
                                            double sipAnnualReturn) 
    {

        // Step 1: Home loan EMI calculation
           double loanAmount = homePrice - downPayment;
           double emi =calculateEMI(loanAmount, loanRate, tenureYears);
           
           
        // Step 2:  Annual Amortization
        generateAnnualAmortization(loanAmount, loanRate, tenureYears);

        // Step 3: Home value calculation
        double homeValueAtEnd = calculateHomeValue(homePrice, salvageValue, appreciationRate, tenureYears);

        // Step 4: SIP value calculation
        double sipValueAtEnd = calculateSIPValue(sipMonthlyContribution, sipAnnualReturn, tenureYears);

        System.out.println("\nSummary after " + tenureYears + " years:");
        System.out.println("Loan Amount: ₹" + loanAmount);
        System.out.println("Home Value at the end of " + tenureYears + " years: ₹" + String.format("%.2f", homeValueAtEnd));
        System.out.println("Total money accumulated through EMI " + tenureYears + " years: ₹" + String.format("%.2f", emi *12*tenureYears));
        System.out.println("Total SIP Investment Value after " + tenureYears + " years: ₹" + String.format("%.2f", sipValueAtEnd));
        System.out.println("Total SIP Contributions over " + tenureYears + " years: ₹" + sipMonthlyContribution * 12 * tenureYears);
    }

    public static void main(String[] args) {
        double homePrice = 50000;
        double downPayment = 1000;
        double loanRate = 8;
        int tenureYears = 5;
        double salvageValue = 500;
        double appreciationRate = 0.05;
        //
        double sipMonthlyContribution = 2500;
        double sipAnnualReturn = 12;

        investmentComparison(homePrice, downPayment, loanRate, tenureYears, salvageValue, appreciationRate, sipMonthlyContribution, sipAnnualReturn);
    }
}
