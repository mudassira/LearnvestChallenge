package com.learnvest.challenge;

import java.util.List;

import com.learnvest.challenge.input.CollateralPayment;
import com.learnvest.challenge.input.DebtTranche;
import com.learnvest.challenge.output.DebtTrancheOutput;
import com.learnvest.challenge.output.Output;

public class DebtTrancheCalculator implements Calculator {

	private DebtTranche debtTranche;
	private double remainingBalance;
	
	public DebtTrancheCalculator(DebtTranche debtTranche) {
		super();
		this.debtTranche = debtTranche;
		this.remainingBalance = debtTranche.initialPrincipal();
	}

	@Override
	public Output calculate(CollateralPayment collatPayment) {
		
		double beginningBalance = remainingBalance;
		double expectedInterest = remainingBalance * debtTranche.rateOfReturn(); //expected interest
																				//may be different from actual interest
																				//if not enough interest left in collateral
		double actualInterest = 0;
		if(expectedInterest < collatPayment.getInterestPayment())
		{
			actualInterest = expectedInterest;
			collatPayment.setInterestPayment(collatPayment.getInterestPayment() - actualInterest);
		}
		else
		{
			actualInterest = collatPayment.getInterestPayment();
			collatPayment.setInterestPayment(0);
		}
		
		double actualPrincipal = 0;		
		if(collatPayment.getPrincipalPayment() > 0)
		{
			if(remainingBalance  < collatPayment.getPrincipalPayment())
			{
				actualPrincipal = remainingBalance;
				collatPayment.setPrincipalPayment(collatPayment.getPrincipalPayment()-actualPrincipal);
				remainingBalance = 0;
			}
			else
			{
				actualPrincipal = collatPayment.getPrincipalPayment();
				collatPayment.setPrincipalPayment(0);
				remainingBalance -= actualPrincipal;
			}
		}
		collatPayment.setPaymentAmount(collatPayment.getInterestPayment() + collatPayment.getPrincipalPayment());
		
		double actualPayment = actualInterest + actualPrincipal;
		
		return new DebtTrancheOutput(beginningBalance, expectedInterest, actualPayment, remainingBalance);
		
	}

	@Override
	public double calculateYTM(List<? extends Output> outputList, YTMCalculator ytmCalc) {
		
		double[] payments = new double[outputList.size()];
		int i = 0;
		for(Output output: outputList)
		{
			payments[i] = ((DebtTrancheOutput)output).actualPayment();
			i++;
		}
		
		return ytmCalc.calculateYTM(this.debtTranche.initialPrincipal(), payments);
		
	}

	@Override
	public double calculatePrice(List<? extends Output> outputList, double yield, YTMCalculator ytmCalc)
	{
		double[] payments = new double[outputList.size()];
		int i = 0;
		for(Output output: outputList)
		{
			payments[i] = ((DebtTrancheOutput)output).actualPayment();
			i++;
		}
		
		return ytmCalc.calculatePrice(payments, yield);
	}

	
}
