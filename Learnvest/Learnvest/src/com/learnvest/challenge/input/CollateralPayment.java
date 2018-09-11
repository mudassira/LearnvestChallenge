package com.learnvest.challenge.input;

public class CollateralPayment {

	protected double paymentAmount;
	protected double interestPayment;
	protected double principalPayment;
	protected double beginningBalance;
	protected double endingBalance;
	protected double prepaymentAmount;
	protected int year;
	
	public CollateralPayment(double paymentAmount, double interestPayment, double principalPayment,
			double beginningBalance, double endingBalance, double prepaymentAmount, int year) {
		super();
		this.paymentAmount = paymentAmount;
		this.interestPayment = interestPayment;
		this.principalPayment = principalPayment;
		this.beginningBalance = beginningBalance;
		this.endingBalance = endingBalance;
		this.prepaymentAmount = prepaymentAmount;
		this.year = year;
	}

	@Override
	public String toString() {
		return "CollateralPayment [paymentAmount=" + paymentAmount + ", interestPayment=" + interestPayment
				+ ", principalPayment=" + principalPayment + ", beginningBalance=" + beginningBalance
				+ ", endingBalance=" + endingBalance + ", prepaymentAmount=" + prepaymentAmount + ", year=" + year
				+ "]";
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public double getInterestPayment() {
		return interestPayment;
	}

	public void setInterestPayment(double interestPayment) {
		this.interestPayment = interestPayment;
	}

	public double getPrincipalPayment() {
		return principalPayment;
	}

	public void setPrincipalPayment(double principalPayment) {
		this.principalPayment = principalPayment;
	}

	public double getBeginningBalance() {
		return beginningBalance;
	}

	public void setBeginningBalance(double beginningBalance) {
		this.beginningBalance = beginningBalance;
	}

	public double getEndingBalance() {
		return endingBalance;
	}

	public void setEndingBalance(double endingBalance) {
		this.endingBalance = endingBalance;
	}

	public double getPrepaymentAmount() {
		return prepaymentAmount;
	}

	public void setPrepaymentAmount(double prepaymentAmount) {
		this.prepaymentAmount = prepaymentAmount;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
	
	
}
