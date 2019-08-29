package com.credit.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.credit.dto.PaymentPlan;
import com.credit.dto.PaymentPlanRequest;
import com.credit.dto.PaymentPlanResponse;
import com.credit.exceptions.MyForbiddenException;
import com.credit.util.Converter;

@Service
public class CreditServiceImpl implements CreditService {

	public PaymentPlanResponse generatePlan(PaymentPlanRequest paymentPlanRequest) {
		validation(paymentPlanRequest);
		
		int duration = paymentPlanRequest.getDuration();
		double interestRate = paymentPlanRequest.getNominalRate();
		BigDecimal monthlyRate = BigDecimal.valueOf(interestRate/12/100).setScale(5, RoundingMode.HALF_UP);
		BigDecimal loanAmount = paymentPlanRequest.getLoanAmount();
		String startDate = paymentPlanRequest.getStartDate();
		
		BigDecimal monthlyPaymentAmount = calculateMonthlyPayment(duration, monthlyRate, loanAmount);
		
		BigDecimal totalPayment= BigDecimal.ZERO;
		List<PaymentPlan> list = new ArrayList<>();
		for (int i = 0; i < duration; i++) {
			BigDecimal initialPrincipal = loanAmount.subtract(totalPayment).setScale(2, RoundingMode.HALF_UP);
			BigDecimal interest = calculateMonthlyInterest(interestRate, initialPrincipal);
			BigDecimal principal= monthlyPaymentAmount.subtract(interest).setScale(1, RoundingMode.HALF_UP);
			if(monthlyPaymentAmount.compareTo(initialPrincipal.add(interest))==1) {
				principal = initialPrincipal;
				monthlyPaymentAmount = principal.add(interest);
			}
			totalPayment = totalPayment.add(principal);
			BigDecimal remainingPrincipal = loanAmount.subtract(totalPayment).setScale(2, RoundingMode.HALF_UP);
			PaymentPlan paymentPlan = new PaymentPlan(monthlyPaymentAmount, principal, 
								interest, initialPrincipal, remainingPrincipal, Converter.stringToLocalDate(startDate).plusMonths(i));
		
			list.add(paymentPlan);
		}
		PaymentPlanResponse paymentPlanResponse = new PaymentPlanResponse(list.toArray(new PaymentPlan[list.size()]));
		
		return paymentPlanResponse;
	}

	private BigDecimal calculateMonthlyPayment(int duration, BigDecimal monthlyRate, BigDecimal loanAmount) {
		BigDecimal sub = loanAmount.multiply(monthlyRate);
		BigDecimal divisor = BigDecimal.ONE.subtract((BigDecimal.ONE.add(monthlyRate)).pow(-1*duration,MathContext.DECIMAL128));
		BigDecimal monthlyPaymentAmount = sub.divide(divisor,MathContext.DECIMAL128).setScale(2,RoundingMode.HALF_UP);
		
		return monthlyPaymentAmount;
	}

	private void validation(PaymentPlanRequest paymentPlanRequest) {
		if(paymentPlanRequest.getLoanAmount()==null 
				|| paymentPlanRequest.getLoanAmount().compareTo(BigDecimal.ZERO)!=1){
			throw new MyForbiddenException();
		}
		if(paymentPlanRequest.getNominalRate()==0) { 
			throw new MyForbiddenException();
		}
	}

	private BigDecimal calculateMonthlyInterest(double interestRate, BigDecimal initialPrincipal) {
		BigDecimal interest = BigDecimal.valueOf(interestRate)
				.multiply(BigDecimal.valueOf(30))
				.multiply(initialPrincipal)
				.divide(BigDecimal.valueOf(360),MathContext.DECIMAL128)
				.divide(BigDecimal.valueOf(100),MathContext.DECIMAL128);
		
		return interest.setScale(2,RoundingMode.HALF_UP);
	}
}
