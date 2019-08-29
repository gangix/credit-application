package com.credit.dto;

import java.math.BigDecimal;


public class PaymentPlanRequest {
	
	private BigDecimal loanAmount;
	private double nominalRate;
	private String startDate;
	private int duration;
	
	public PaymentPlanRequest() {
	}
	
	public PaymentPlanRequest(BigDecimal loanAmount, double nominalRate, String startDate, int duration) {
		super();
		this.loanAmount = loanAmount;
		this.nominalRate = nominalRate;
		this.startDate = startDate;
		this.duration = duration;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getNominalRate() {
		return nominalRate;
	}

	public void setNominalRate(double nominalRate) {
		this.nominalRate = nominalRate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
