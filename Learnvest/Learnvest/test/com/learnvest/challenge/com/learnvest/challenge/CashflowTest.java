package com.learnvest.challenge.com.learnvest.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.learnvest.challenge.JavaCDOImplementation;
import com.learnvest.challenge.input.Collateral;
import com.learnvest.challenge.input.DebtTranche;
import com.learnvest.challenge.input.EquityTranche;
import com.learnvest.challenge.input.Tranche;
import com.learnvest.challenge.output.DebtTrancheOutput;
import com.learnvest.challenge.output.EquityTrancheOutput;
import com.learnvest.challenge.output.Output;

public class CashflowTest {

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
        
        Map<Tranche, List<Output>> results = new JavaCDOImplementation().modelCDO(allTranches, collateral);
        assertTrue(!results.isEmpty());
        assertEquals(results.keySet().size(),5);
    
        assertEquals(results.get(AAA).size(),25);
        assertEquals(results.get(AA).size(),25);
        assertEquals(results.get(A).size(),25);
        assertEquals(results.get(BBB).size(),25);
        assertEquals(results.get(equity).size(),25);
        
        
        List<Output> AAAcfs = results.get(AAA);
        
        DebtTrancheOutput AAAfirstCF = (DebtTrancheOutput) AAAcfs.get(0);
        DebtTrancheOutput AAAsecondCF = (DebtTrancheOutput) AAAcfs.get(1);
        DebtTrancheOutput AAAtenthCF = (DebtTrancheOutput) AAAcfs.get(9);
        DebtTrancheOutput AAAlastCF = (DebtTrancheOutput) AAAcfs.get(24);
        
        assertEquals(AAAfirstCF.beginningBalance(),100000,0.1);
        assertEquals(AAAfirstCF.expectedInterest(),5000,0.1);
        assertEquals(AAAfirstCF.actualPayment(),62114.73275,0.1);
        assertEquals(AAAfirstCF.endingBalance(),42885.26725,0.1);

        assertEquals(AAAsecondCF.beginningBalance(),42885.26725,0.1);
        assertEquals(AAAsecondCF.expectedInterest(),2144.263363,0.1);
        assertEquals(AAAsecondCF.actualPayment(),45029.53061,0.1);
        assertEquals(AAAsecondCF.endingBalance(),0,0.1);
        
        assertEquals(AAAtenthCF.beginningBalance(),0,0.1);
        assertEquals(AAAtenthCF.expectedInterest(),0,0.1);
        assertEquals(AAAtenthCF.actualPayment(),0,0.1);
        assertEquals(AAAtenthCF.endingBalance(),0,0.1);
        
        assertEquals(AAAlastCF.beginningBalance(),0,0.1);
        assertEquals(AAAlastCF.expectedInterest(),0,0.1);
        assertEquals(AAAlastCF.actualPayment(),0,0.1);
        assertEquals(AAAlastCF.endingBalance(),0,0.1);
    

        List<Output> AAcfs = results.get(AA);
        
        DebtTrancheOutput AAfirstCF = (DebtTrancheOutput) AAcfs.get(0);
        DebtTrancheOutput AAsecondCF = (DebtTrancheOutput) AAcfs.get(1);
        DebtTrancheOutput AAtenthCF = (DebtTrancheOutput) AAcfs.get(9);
        DebtTrancheOutput AAlastCF = (DebtTrancheOutput) AAcfs.get(24);
        
        assertEquals(AAfirstCF.beginningBalance(),100000,0.1);
        assertEquals(AAfirstCF.expectedInterest(),5000,0.1);
        assertEquals(AAfirstCF.actualPayment(),5000,0.1);
        assertEquals(AAfirstCF.endingBalance(),100000,0.1);

        assertEquals(AAsecondCF.beginningBalance(),100000,0.1);
        assertEquals(AAsecondCF.expectedInterest(),5000,0.1);
        assertEquals(AAsecondCF.actualPayment(),13254.74711,0.1);
        assertEquals(AAsecondCF.endingBalance(),91745.25289,0.1);
        
        assertEquals(AAtenthCF.beginningBalance(),0,0.1);
        assertEquals(AAtenthCF.expectedInterest(),0,0.1);
        assertEquals(AAtenthCF.actualPayment(),0,0.1);
        assertEquals(AAtenthCF.endingBalance(),0,0.1);
        
        assertEquals(AAlastCF.beginningBalance(),0,0.1);
        assertEquals(AAlastCF.expectedInterest(),0,0.1);
        assertEquals(AAlastCF.actualPayment(),0,0.1);
        assertEquals(AAlastCF.endingBalance(),0,0.1);


        
        List<Output> Acfs = results.get(A);
        
        DebtTrancheOutput AfirstCF = (DebtTrancheOutput) Acfs.get(0);
        DebtTrancheOutput AsecondCF = (DebtTrancheOutput) Acfs.get(1);
        DebtTrancheOutput AtenthCF = (DebtTrancheOutput) Acfs.get(9);
        DebtTrancheOutput AlastCF = (DebtTrancheOutput) Acfs.get(24);
        
        assertEquals(AfirstCF.beginningBalance(),100000,0.1);
        assertEquals(AfirstCF.expectedInterest(),5000,0.1);
        assertEquals(AfirstCF.actualPayment(),5000,0.1);
        assertEquals(AfirstCF.endingBalance(),100000,0.1);

        assertEquals(AsecondCF.beginningBalance(),100000,0.1);
        assertEquals(AsecondCF.expectedInterest(),5000,0.1);
        assertEquals(AsecondCF.actualPayment(),5000,0.1);
        assertEquals(AsecondCF.endingBalance(),100000,0.1);
        
        assertEquals(AtenthCF.beginningBalance(),0,0.1);
        assertEquals(AtenthCF.expectedInterest(),0,0.1);
        assertEquals(AtenthCF.actualPayment(),0,0.1);
        assertEquals(AtenthCF.endingBalance(),0,0.1);
        
        assertEquals(AlastCF.beginningBalance(),0,0.1);
        assertEquals(AlastCF.expectedInterest(),0,0.1);
        assertEquals(AlastCF.actualPayment(),0,0.1);
        assertEquals(AlastCF.endingBalance(),0,0.1);


        
        List<Output> BBBcfs = results.get(BBB);
        
        DebtTrancheOutput BBBfirstCF = (DebtTrancheOutput) BBBcfs.get(0);
        DebtTrancheOutput BBBsecondCF = (DebtTrancheOutput) BBBcfs.get(1);
        DebtTrancheOutput BBBtenthCF = (DebtTrancheOutput) BBBcfs.get(9);
        DebtTrancheOutput BBBlastCF = (DebtTrancheOutput) BBBcfs.get(24);
        
        assertEquals(BBBfirstCF.beginningBalance(),100000,0.1);
        assertEquals(BBBfirstCF.expectedInterest(),5000,0.1);
        assertEquals(BBBfirstCF.actualPayment(),5000,0.1);
        assertEquals(BBBfirstCF.endingBalance(),100000,0.1);

        assertEquals(BBBsecondCF.beginningBalance(),100000,0.1);
        assertEquals(BBBsecondCF.expectedInterest(),5000,0.1);
        assertEquals(BBBsecondCF.actualPayment(),5000,0.1);
        assertEquals(BBBsecondCF.endingBalance(),100000,0.1);
        
        assertEquals(BBBtenthCF.beginningBalance(),57025.74758,0.1);
        assertEquals(BBBtenthCF.expectedInterest(),2851.287379,0.1);
        assertEquals(BBBtenthCF.actualPayment(),23621.37868,0.1);
        assertEquals(BBBtenthCF.endingBalance(),36255.65628,0.1);
        
        assertEquals(BBBlastCF.beginningBalance(),0,0.1);
        assertEquals(BBBlastCF.expectedInterest(),0,0.1);
        assertEquals(BBBlastCF.actualPayment(),0,0.1);
        assertEquals(BBBlastCF.endingBalance(),0,0.1);

        List<Output> equitycfs = results.get(equity);
        
        EquityTrancheOutput equityfirstCF = (EquityTrancheOutput) equitycfs.get(0);
        EquityTrancheOutput equitysecondCF = (EquityTrancheOutput) equitycfs.get(1);
        EquityTrancheOutput equitytenthCF = (EquityTrancheOutput) equitycfs.get(9);
        EquityTrancheOutput equitylastCF = (EquityTrancheOutput) equitycfs.get(24);
        
        assertEquals(equityfirstCF.paymentRecieved(),15000,0.1);
        
        assertEquals(equitysecondCF.paymentRecieved(),13857.70535,0.1);
        
        assertEquals(equitytenthCF.paymentRecieved(),8140.514952,0.1);
        
        assertEquals(equitylastCF.paymentRecieved(),3422.399869,0.1);
        
    }
    
}
