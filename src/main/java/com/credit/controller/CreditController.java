package com.credit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.credit.dto.PaymentPlanRequest;
import com.credit.dto.PaymentPlanResponse;
import com.credit.service.CreditService;

@RestController
public class CreditController {

	@Autowired
	private CreditService creditService;

	@PostMapping(path = "/generate-plan")
	public ResponseEntity<PaymentPlanResponse> generatePlan(@RequestBody PaymentPlanRequest creditRequest) {
		return ResponseEntity.ok(creditService.generatePlan(creditRequest));
	}

}
