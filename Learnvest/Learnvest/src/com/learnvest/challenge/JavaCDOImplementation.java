package com.learnvest.challenge;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.learnvest.challenge.input.DebtTranche;
import com.learnvest.challenge.input.EquityTranche;
import com.learnvest.challenge.input.Collateral;
import com.learnvest.challenge.input.CollateralPayment;
import com.learnvest.challenge.input.Tranche;
import com.learnvest.challenge.output.Output;

/**
 * Not a working implementation, only used for sample test.
 *
 * Extend this trait and implement the method in your own code. We will be running tests against this method signature.
 * Lists of output objects should represent the same step in time. If there is no payment for a tranche in a specific time
 * step make sure to still output a value that include beginning and ending balance for the debt tranches or Zero for the
 * payment received for the equity tranche.
 */

public class JavaCDOImplementation implements JavaChallengeImplementation{
    @Override
    public Map<Tranche, List<Output>> modelCDO(List<Tranche> tranches, Collateral collateral) {
        
    	List<CollateralPayment> paymentList = CollateralPaymentCalculator.calculateCollateralPayments(collateral);
    	TrancheOutputCalculator trancheCalc = new TrancheOutputCalculator();    	
    	Map<Tranche,List<Output>> trancheOutput = trancheCalc.calculateTrancheOutput(paymentList,  tranches);
		return trancheOutput;
    }
    
    public Map<Tranche, Double> calculateYTM(Map<Tranche, List<Output>> tranchesWithCFs)
    {
    	TrancheOutputCalculator trancheCalc = new TrancheOutputCalculator();
    	Map<Tranche,Double> trancheYTMs = trancheCalc.calculateTrancheYTM(tranchesWithCFs);
    	return trancheYTMs;
    }
    
    
    public static void main(String[] args)
    {
    
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
        
        JavaCDOImplementation javaImpl = new JavaCDOImplementation(); 
        
        Map<Tranche, List<Output>> results = javaImpl.modelCDO(allTranches, collateral);
        Map<Tranche, Double> ytms = javaImpl.calculateYTM(results);
        
        System.out.println("Tranche AAA YTM: " + ytms.get(AAA));
        System.out.println("Tranche AA YTM: " + ytms.get(AA));
        System.out.println("Tranche A YTM: " + ytms.get(A));
        System.out.println("Tranche BBB YTM: " + ytms.get(BBB));
        System.out.println("Tranche Equity YTM: " + ytms.get(equity));
        
    
    }
    
}
