package com.credit.dto;

import java.util.Arrays;

public class PaymentPlanResponse {
	private PaymentPlan[] array;

	public PaymentPlanResponse() {
	}
	
	public PaymentPlanResponse(PaymentPlan[] array) {
		super();
		this.array = array;
	}

	public PaymentPlan[] getArray() {
		return Arrays.copyOf(array, array.length);
	}

	public void setArray(PaymentPlan[] array) {
		this.array = array;
	}

}
