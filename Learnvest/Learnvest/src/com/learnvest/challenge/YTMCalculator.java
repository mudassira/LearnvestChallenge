package com.learnvest.challenge;

public class YTMCalculator {

	public double calculateYTM(double initInvestment, double[] paymentList) {

		final double EPSILON = 0.01;
		
		double yield1 = 0, yield2 = 1;
		double yieldMid = (yield1 + yield2)/2;
		
		double estimatedPrice = calculatePrice(paymentList, yieldMid); 
	
		while( Math.abs(estimatedPrice - initInvestment) > EPSILON )
		{
			if(estimatedPrice > initInvestment)
			{
				yield1 = yieldMid;
			}
			else
			{
				yield2 = yieldMid;
			}
			yieldMid = (yield1 + yield2)/2;
			estimatedPrice = calculatePrice(paymentList,yieldMid);
		}
		
		return yieldMid;
		
	}

	public double calculatePrice(double[] paymentList, double yield)
	{
		double discountFactor = 1 + yield;
		double price = 0;
		
		int i = 0;
		
		
		while(i<paymentList.length )
		{
			double payment = paymentList[i];
			price += payment / (Math.pow(discountFactor,(i+1)));
			i++;
		}
		
		return price;
	}
}
