package com.credit.service;

import com.credit.dto.PaymentPlanRequest;
import com.credit.dto.PaymentPlanResponse;

public interface CreditService {

	PaymentPlanResponse generatePlan(PaymentPlanRequest c);

}
