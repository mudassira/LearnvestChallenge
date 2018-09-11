package com.learnvest.challenge;

import java.util.LinkedList;
import java.util.List;

import com.learnvest.challenge.input.Collateral;
import com.learnvest.challenge.input.CollateralPayment;

public class CollateralPaymentCalculator {

	public static List<CollateralPayment> calculateCollateralPayments(Collateral collateral)
	{
		List<CollateralPayment> collatPaymentList = new LinkedList<CollateralPayment>();
		
		double beginningBalance = collateral.beginningBalance(); 
		double interestRate = collateral.rateOfReturn();
		double prepaymentRate = collateral.prePaymentRate();
		
		for(int yearsRemaining = collateral.numberOfYears(); yearsRemaining > 0; yearsRemaining--)
		{
			double paymentAmount = beginningBalance * (interestRate * Math.pow((1 + interestRate),yearsRemaining))
									/ (Math.pow((1 + interestRate),yearsRemaining) - 1);
			double interestPayment = interestRate * beginningBalance;
			double principalPayment = paymentAmount - interestPayment;
			double endingBalance = beginningBalance - principalPayment;
			double prepaymentAmount = prepaymentRate * endingBalance;
			principalPayment += prepaymentAmount;
			endingBalance = beginningBalance - principalPayment;
			paymentAmount = interestPayment + principalPayment;
			
			CollateralPayment collatPayment = new CollateralPayment(paymentAmount,interestPayment,principalPayment,
					beginningBalance, endingBalance, prepaymentAmount, collateral.numberOfYears() - yearsRemaining + 1);
			
			collatPaymentList.add(collatPayment);
			beginningBalance = endingBalance;
		}
	
		return collatPaymentList;
	}
}
