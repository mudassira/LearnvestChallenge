package com.learnvest.challenge.com.learnvest.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.learnvest.challenge.CalculatorFactory;
import com.learnvest.challenge.DebtTrancheCalculator;
import com.learnvest.challenge.JavaCDOImplementation;
import com.learnvest.challenge.YTMCalculator;
import com.learnvest.challenge.exceptions.UnsupportedCalculatorException;
import com.learnvest.challenge.input.Collateral;
import com.learnvest.challenge.input.DebtTranche;
import com.learnvest.challenge.input.EquityTranche;
import com.learnvest.challenge.input.Tranche;
import com.learnvest.challenge.output.DebtTrancheOutput;
import com.learnvest.challenge.output.EquityTrancheOutput;
import com.learnvest.challenge.output.Output;

public class YTMTest {
	
	@Test
	public void PriceYieldTestZC()
	{
		Tranche debtTranche = new DebtTranche(100,0.05);
		CalculatorFactory factory = new CalculatorFactory();
		try {
			DebtTrancheCalculator calc = (DebtTrancheCalculator) factory.getTrancheCalculator(debtTranche);
			YTMCalculator ytmCalc = new YTMCalculator();
			List<DebtTrancheOutput> trancheOutputList = new LinkedList<DebtTrancheOutput>();
			DebtTrancheOutput trancheOutput = new DebtTrancheOutput(100, 5, 105, 0);
			trancheOutputList.add(trancheOutput);
			double price = calc.calculatePrice(trancheOutputList, 0.05,ytmCalc);
			assertEquals(100, price, 0.01);
			
			
			double yield = calc.calculateYTM(trancheOutputList,ytmCalc);
			assertEquals(0.05,yield,0.001);
		
		
		
		} catch (UnsupportedCalculatorException e) {}
		
		
	}

	@Test
	public void PriceYieldTestCB()
	{
		DebtTranche debtTranche = new DebtTranche(90,0.05);
		CalculatorFactory factory = new CalculatorFactory();
		try {
			DebtTrancheCalculator calc = (DebtTrancheCalculator) factory.getTrancheCalculator(debtTranche);
			YTMCalculator ytmCalc = new YTMCalculator();
			List<DebtTrancheOutput> trancheOutputList = new LinkedList<DebtTrancheOutput>();
			DebtTrancheOutput trancheOutput1 = new DebtTrancheOutput(100, 5, 5, 100);
			DebtTrancheOutput trancheOutput2 = new DebtTrancheOutput(100, 5, 5, 100);
			DebtTrancheOutput trancheOutput3 = new DebtTrancheOutput(100, 5, 5, 100);
			DebtTrancheOutput trancheOutput4 = new DebtTrancheOutput(100, 5, 5, 100);
			DebtTrancheOutput trancheOutput5 = new DebtTrancheOutput(100, 5, 105, 100);
			trancheOutputList.add(trancheOutput1);
			trancheOutputList.add(trancheOutput2);
			trancheOutputList.add(trancheOutput3);
			trancheOutputList.add(trancheOutput4);
			trancheOutputList.add(trancheOutput5);
			
			
			double price = calc.calculatePrice(trancheOutputList, 0.05,ytmCalc);
			assertEquals(100, price, 0.01);
			
			
			double yield = calc.calculateYTM(trancheOutputList,ytmCalc);
			assertEquals(0.0747,yield,0.001);
		
		
		
		} catch (UnsupportedCalculatorException e) {}
		
		
	}
	
	
	 @Test
	    public void trancheTest(){
	        Tranche AAA =  new DebtTranche(100000, .05);
	        Tranche AA = new DebtTranche(100000, .05);
	        Tranche A = new DebtTranche(100000, .05);
	        Tranche BBB = new DebtTranche(100000, .05);
	        Tranche equity = new EquityTranche(50000);

	        List<Tranche> allTranches = new LinkedList<>();
	        allTranches.add(AAA);
	        allTranches.add(AA);
	        allTranches.add(A);
	        allTranches.add(BBB);
	        allTranches.add(equity);

	        Collateral collateral = new Collateral(500000, 25, .07, .10);
	        
	        JavaCDOImplementation model = new JavaCDOImplementation();
	        
	        Map<Tranche, List<Output>> results = model.modelCDO(allTranches, collateral);
	        Map<Tranche, Double> ytms = model.calculateYTM(results);
	        
	        assertEquals(0.04999, ytms.get(AAA),0.0001);
	        assertEquals(0.05000, ytms.get(AA),0.0001);
	        assertEquals(0.05000, ytms.get(A),0.0001);
	        assertEquals(0.05000, ytms.get(BBB),0.0001);
	        assertEquals(0.24604, ytms.get(equity),0.0001);
	        
	 }
}
