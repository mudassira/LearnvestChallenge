package com.learnvest.challenge;

import java.util.List;

import com.learnvest.challenge.input.CollateralPayment;
import com.learnvest.challenge.input.EquityTranche;
import com.learnvest.challenge.output.EquityTrancheOutput;
import com.learnvest.challenge.output.Output;

public class EquityTrancheCalculator implements Calculator {

	private EquityTranche equityTranche;
	
	public EquityTrancheCalculator(EquityTranche equityTranche) {
		super();
		this.equityTranche = equityTranche;
	}

	@Override
	public Output calculate(CollateralPayment collatPayment) {
		double actualPayment = collatPayment.getPaymentAmount();
		collatPayment.setPaymentAmount(0);
		collatPayment.setInterestPayment(0);
		collatPayment.setPrincipalPayment(0);
		collatPayment.setPrepaymentAmount(0);
		return new EquityTrancheOutput(actualPayment);
	}
	
	@Override
	public double calculateYTM(List<? extends Output> outputList, YTMCalculator ytmCalc) {
		
		double[] payments = new double[outputList.size()];
		int i = 0;
		for(Output output: outputList)
		{
			payments[i] = ((EquityTrancheOutput)output).paymentRecieved();
			i++;
		}
		
		return ytmCalc.calculateYTM(this.equityTranche.faceValue(), payments);
		
	}

	@Override
	public double calculatePrice(List<? extends Output> outputList, double yield, YTMCalculator ytmCalc)
	{
		double[] payments = new double[outputList.size()];
		int i = 0;
		for(Output output: outputList)
		{
			payments[i] = ((EquityTrancheOutput)output).paymentRecieved();
			i++;
		}
		
		return ytmCalc.calculatePrice(payments, yield);
	}


}
