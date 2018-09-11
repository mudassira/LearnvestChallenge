package com.learnvest.challenge;

import java.util.List;

import com.learnvest.challenge.input.CollateralPayment;
import com.learnvest.challenge.output.Output;

public interface Calculator {

	public Output calculate(CollateralPayment collatPayment);
	public double calculateYTM(List<? extends Output> outputList, YTMCalculator ytmCalc);
	public double calculatePrice(List<? extends Output> outputList, double yield, YTMCalculator ytmCalc);
}
