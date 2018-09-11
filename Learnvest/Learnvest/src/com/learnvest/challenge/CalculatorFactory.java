package com.learnvest.challenge;

import java.util.HashMap;
import java.util.Map;

import com.learnvest.challenge.exceptions.UnsupportedCalculatorException;
import com.learnvest.challenge.input.DebtTranche;
import com.learnvest.challenge.input.EquityTranche;
import com.learnvest.challenge.input.Tranche;

public class CalculatorFactory {

	public static Map<Tranche, Calculator> calculatorMap;
	
	public CalculatorFactory()
	{
		calculatorMap = new HashMap<Tranche, Calculator>();
	}
	
	
	public Calculator getTrancheCalculator(Tranche tranche) throws UnsupportedCalculatorException
	{
		Calculator calc = calculatorMap.get(tranche);
		if( calc == null )
		{
			if(tranche instanceof DebtTranche)
			{
				calc = new DebtTrancheCalculator((DebtTranche)tranche);
			}
			else if(tranche instanceof EquityTranche)
			{
				calc = new EquityTrancheCalculator((EquityTranche)tranche);
			} 
			else
			{
				throw new UnsupportedCalculatorException("Unsupported tranche type.");
			}
			calculatorMap.put(tranche, calc);
		}
		return calc;
	}

}
