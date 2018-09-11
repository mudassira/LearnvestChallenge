package com.learnvest.challenge.com.learnvest.challenge;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.learnvest.challenge.CollateralPaymentCalculator;
import com.learnvest.challenge.input.Collateral;
import com.learnvest.challenge.input.CollateralPayment;

public class CollateralTest {

	@Test
	public void collateralPaymentTest()
	{
		Collateral collateral = new Collateral(500000, 25, .07, .10);
		List<CollateralPayment> paymentList = CollateralPaymentCalculator.calculateCollateralPayments(collateral);
	
		assertEquals(25,paymentList.size());
		
		CollateralPayment payment1 = paymentList.get(0);
		assertEquals(92114.73275, payment1.getPaymentAmount(),0.01);
		assertEquals(35000, payment1.getInterestPayment(),0.01);
		assertEquals(57114.73275, payment1.getPrincipalPayment(),0.01);
		assertEquals(500000, payment1.getBeginningBalance(),0.01);
		assertEquals(442885.2673, payment1.getEndingBalance(),0.01);
		assertEquals(49209.47414, payment1.getPrepaymentAmount(),0.01);
		
		
		CollateralPayment payment10 = paymentList.get(9);
		assertEquals(31761.89364, payment10.getPaymentAmount(),0.01);
		assertEquals(10991.80233, payment10.getInterestPayment(),0.01);
		assertEquals(20770.0913, payment10.getPrincipalPayment(),0.01);
		assertEquals(157025.7476, payment10.getBeginningBalance(),0.01);
		assertEquals(136255.6563, payment10.getEndingBalance(),0.01);
		assertEquals(15139.51736, payment10.getPrepaymentAmount(),0.01);
		
		
		CollateralPayment paymentlast = paymentList.get(24);
		assertEquals(3422.399869, paymentlast.getPaymentAmount(),0.01);
		assertEquals(223.8953185, paymentlast.getInterestPayment(),0.01);
		assertEquals(3198.50455, paymentlast.getPrincipalPayment(),0.01);
		assertEquals(3198.50455, paymentlast.getBeginningBalance(),0.01);
		assertEquals(0, paymentlast.getEndingBalance(),0.01);
		assertEquals(0, paymentlast.getPrepaymentAmount(),0.01);

		
		
	}
}
