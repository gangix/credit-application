package com.credit.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credit.util.Converter;

public class PaymentPlan {
	private BigDecimal borrowerPaymentAmount;
	private BigDecimal principal;
	private BigDecimal interest;
	private BigDecimal initialOutstandingPrincipal;
	private BigDecimal remainingOutstandingPrincipal;
	private String date;

	public PaymentPlan() {
	}
	
	public PaymentPlan(BigDecimal monthlyPaymentAmount, BigDecimal principal, BigDecimal interest,
			BigDecimal initialPrincipal, BigDecimal remainingPrincipal, LocalDate localDate) {
		super();
		this.borrowerPaymentAmount = monthlyPaymentAmount;
		this.principal = principal;
		this.interest = interest;
		this.initialOutstandingPrincipal = initialPrincipal;
		this.remainingOutstandingPrincipal = remainingPrincipal;
		this.date = Converter.localDateToString(localDate);
	}

	public BigDecimal getBorrowerPaymentAmount() {
		return borrowerPaymentAmount;
	}

	public void setBorrowerPaymentAmount(BigDecimal borrowerPaymentAmount) {
		this.borrowerPaymentAmount = borrowerPaymentAmount;
	}

	public BigDecimal getPrincipal() {
		return principal;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getInitialOutstandingPrincipal() {
		return initialOutstandingPrincipal;
	}

	public void setInitialOutstandingPrincipal(BigDecimal initialOutstandingPrincipal) {
		this.initialOutstandingPrincipal = initialOutstandingPrincipal;
	}

	public BigDecimal getRemainingOutstandingPrincipal() {
		return remainingOutstandingPrincipal;
	}

	public void setRemainingOutstandingPrincipal(BigDecimal remainingOutstandingPrincipal) {
		this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
