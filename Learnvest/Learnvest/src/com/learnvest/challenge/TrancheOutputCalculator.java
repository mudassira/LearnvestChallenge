package com.learnvest.challenge;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.learnvest.challenge.exceptions.UnsupportedCalculatorException;
import com.learnvest.challenge.input.CollateralPayment;
import com.learnvest.challenge.input.Tranche;
import com.learnvest.challenge.output.Output;

public class TrancheOutputCalculator {

	CalculatorFactory factory;
	
	TrancheOutputCalculator()
	{
		factory = new CalculatorFactory();
	}
	
	public Map<Tranche,List<Output>> calculateTrancheOutput(List<CollateralPayment> collateralPayments, List<Tranche> tranches)
	{
				
		Map<Tranche,List<Output>> outputMap = new HashMap<Tranche, List<Output>>(); 		
		for(CollateralPayment collateralPayment: collateralPayments)
		{
			for(Tranche tranche: tranches)
			{
				Calculator calc;
				try {
					calc = factory.getTrancheCalculator(tranche);
					Output trancheOutput = calc.calculate(collateralPayment);
					List<Output> trancheOutputList = outputMap.get(tranche);
					if( trancheOutputList == null )
					{
						trancheOutputList = new LinkedList<Output>();
						outputMap.put(tranche,trancheOutputList);
					}
					trancheOutputList.add(trancheOutput);
				} catch (UnsupportedCalculatorException e) {
					System.out.println("Calculator for tranche type " + tranche.getClass().getName() + " is not supported");
					System.out.println("This tranche will be ignored from cash flow calculations.");
				}
				
			}
			
		}
		
		return outputMap;
	}
	
	public Map<Tranche,Double> calculateTrancheYTM(Map<Tranche,List<Output>> trancheOutput)
	{
		YTMCalculator ytmCalc = new YTMCalculator();
		Map<Tranche,Double> ytmMap = new HashMap<Tranche,Double>();
		
		Iterator<Entry<Tranche, List<Output>>> it = trancheOutput.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<Tranche,List<Output>> pair = (Map.Entry<Tranche,List<Output>>)it.next();
	        
	        Calculator calc;
			try {
				calc = factory.getTrancheCalculator(pair.getKey());
				double trancheYTM = calc.calculateYTM(pair.getValue(), ytmCalc);
				ytmMap.put(pair.getKey(), trancheYTM);
			} catch (UnsupportedCalculatorException e) {
				System.out.println("Calculator for tranche type " + pair.getKey().getClass().getName() + " is not supported");
				System.out.println("This tranche will be ignored from cash flow calculations.");
			}
	        
	        it.remove(); 
	    }
	    return ytmMap;
	}

	
}
