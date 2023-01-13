//*****************************************************************************************************
//
// File: LoanCalculator.java
//
// Student: Leon Krugliakov
//
// Assignment: Loan Calculator - Assignment #2
//
// Course Name: Java I
//
// Course Number: COSC 2050 - 01
//
// Due: February 5, 2020
//
//
// This program asks the user to enter a loan amount, loan interest
// rate, and the term of the loan in years. Then the program computes
// the monthly payment for the loan.
//
// Other files required:
//
//*****************************************************************************************************

//package loancalculator;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.NumberFormat;

public class LoanCalculator 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String contin = "y";
        double loanAmount,
                interestRate;
        int years;
        
        System.out.println("Welcome to the Loan Calculator \n\n");
        
        while(contin.equalsIgnoreCase("y"))
        {
            System.out.println("DATA ENTRY");
            loanAmount = getDoubleWithinRange(sc, "Enter loan amount:\t\t ", 
                    0, 1000000);
            interestRate = getDoubleWithinRange(sc, 
                    "Enter yearly interest rate:\t ", 0, 20);
            years = getIntWithinRange(sc, "Enter number of years:\t\t ", 
                    0, 100);
            displayResults(loanAmount, interestRate, years);
            sc.nextLine();
            contin = getChoiceString(sc, 
                    "Would you like to continue? (y/n):  ", "y", "n");
            
        }
        
        
    }
    
    //*****************************************************************************************************
    
    public static double getDouble(Scanner sc, String prompt)
    {
        String valid = "n";
        double input = 0.0;
        
        while(valid.equalsIgnoreCase("n"))
        {
            
            try
            {
                System.out.print(prompt);
                input = sc.nextDouble();
                valid = "y";
            }
            catch(InputMismatchException e)
            {
                System.out.println("The input you ahve eneterd is invalid, "
                        + "it must be a decimal. Try again!");
                sc.nextLine();
                continue;
            }
        }
        
        return input;
    }
    
    //*****************************************************************************************************
    
    public static double getDoubleWithinRange(Scanner sc, String prompt,
            double min, double max)
    {
        String valid = "n";
        double input = 0.0;
        
        while(valid == "n")
        {
            
            input = getDouble(sc, prompt);
            
            if(input <= min)
            {
                System.out.println("The input you have entered cannot "
                        + "be lower than " + min + ", please try again!");
            }
            else if(input >= max)
            {
                System.out.println("The input you have entered cannot be "
                        + "higher than " + max + ", please try again!");
            }
            else
            {
                valid = "y";
            }
            
        }
        
        return input;
   }
    
    //*****************************************************************************************************

    public static int getInt(Scanner sc, String prompt)
    {
        String valid = "n";
        int input = 0;
        
        while(valid.equalsIgnoreCase("n")){
            
            try
            {
                System.out.print(prompt);
                input = sc.nextInt();
                valid = "y";
            }
            catch(InputMismatchException e)
            {
                System.out.println("The input you ahve eneterd is invalid, "
                        + "it must be an integer. Try again!");
                sc.nextLine();
                continue;
            }
            
        }
        
        return input;
    }
    
    //*****************************************************************************************************
    
    public static int getIntWithinRange(Scanner sc, String prompt,
            int min, int max)
    {
        String valid = "n";
        int input = 0;
        
        while(valid == "n")
        {
            
            input = getInt(sc, prompt);
            
            if(input <= min)
            {
                System.out.println("The input you have entered cannot be "
                        + "lower than " + min + ", please try again!");
            }
            else if(input >= max)
            {
                System.out.println("The input you have entered cannot be "
                        + "higher than " + max + ", please try again!");
            }
            else
            {
                valid = "y";
            }
            
        }
        
        return input;
    }
    
    //*****************************************************************************************************
    
    public static void displayResults(double loanAmount, 
            double interestRate, int years)
    {
        
        NumberFormat dollar = NumberFormat.getCurrencyInstance();
        NumberFormat percent = NumberFormat.getPercentInstance();
        dollar.setMaximumFractionDigits(2);
        percent.setMaximumFractionDigits(2);
        
        int months = years * 12;
        double monthlyInterestRate = interestRate / (100 * 12);
        double monthlyPayment = calculateMonthlyPayment(loanAmount, 
                months, monthlyInterestRate);
        
        System.out.println("FORMATTED RESULTS");
        System.out.println("Loan Amount:\t\t\t " + dollar.format(loanAmount));
        System.out.println("Yearly interest rate:\t\t " 
                + percent.format(interestRate / 100));
        System.out.println("Number of years:\t\t\t " + years);
        System.out.println("Monthly payment:\t\t\t " 
                + dollar.format(monthlyPayment));
    }
    
    //*****************************************************************************************************
    
    private static String getRequiredString(Scanner sc, String prompt)
    {
        String input = "",
                valid = "n";
        
        while(valid == "n"){
            
            System.out.print(prompt);
            input = sc.nextLine();
            
            if(input.isEmpty())
            {
                System.out.println("Please enter an input, this"
                        + " line can't be left blank!");
            }
            else if(!input.isEmpty())
            {
                valid = "y";
            }
            
        }
        
        return input;
        
    }
    
    //*****************************************************************************************************
    
    private static String getChoiceString(Scanner sc, 
            String prompt, String s1, String s2)
    {
        String valid = "no",
                input = "";
        
        while(valid == "no")
        {
            input = getRequiredString(sc, prompt);
            if(input.equalsIgnoreCase(s1))
            {
                valid = "yes";
            }
            else if(input.equalsIgnoreCase(s2))
            {
                valid = "yes";
            }
            else
            {
                System.out.println("The input you have entered is invalid."
                        + " Please enter 'y' or 'n' as your answer!");
                valid = "no";
            }
        }
        
        return input;
    }
    
    //*****************************************************************************************************
    
    public static double calculateMonthlyPayment(double loanAmount, 
            int months, double monthlyInterestRate)
    {
        
        double monthlyPayment = (loanAmount * monthlyInterestRate / 
                (1 - 1 / Math.pow(1 + monthlyInterestRate, months)));
        
        return monthlyPayment;
    }
    
}
