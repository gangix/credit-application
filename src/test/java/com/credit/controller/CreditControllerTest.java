/**
 * 
 */
package com.credit.controller;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.credit.dto.PaymentPlan;
import com.credit.dto.PaymentPlanRequest;
import com.credit.dto.PaymentPlanResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreditControllerTest {

	@Autowired
	private TestRestTemplate template;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void generatePlan_InvalidLoanAmount_ReturnsForbidenStatusCode() throws Exception {
		PaymentPlanRequest request = new PaymentPlanRequest(BigDecimal.valueOf(0), 5.0, "2018-01-01T00:00:01Z", 24);

		String jSon = objectMapper.writeValueAsString(request);
		HttpEntity<Object> requestEntity = getHttpEntity(jSon);
		ResponseEntity<PaymentPlanResponse> response = template.postForEntity("/generate-plan", requestEntity,
				PaymentPlanResponse.class);

		Assert.assertEquals(403, response.getStatusCode().value());
	}

	@Test
	public void generatePlan_ValidLoanAmount_Successfull() throws Exception {
		PaymentPlanRequest request = new PaymentPlanRequest(BigDecimal.valueOf(5000), 5.0, "2018-01-01T00:00:01Z", 24);

		String jSon = objectMapper.writeValueAsString(request);
		HttpEntity<Object> requestEntity = getHttpEntity(jSon);
		ResponseEntity<PaymentPlanResponse> response = template.postForEntity("/generate-plan", requestEntity,
				PaymentPlanResponse.class);

		PaymentPlan[] array = response.getBody().getArray();
		
		Assert.assertEquals(24, array.length);
		Assert.assertEquals(BigDecimal.valueOf(20.83), array[0].getInterest());
		Assert.assertEquals(BigDecimal.ZERO.setScale(2), array[23].getRemainingOutstandingPrincipal());
	}

	@Test
	public void generatePlan_InvalidInterestAmount_ReturnsForbidenStatusCode() throws Exception {
		PaymentPlanRequest request = new PaymentPlanRequest(BigDecimal.valueOf(5000), 0, "2018-01-01T00:00:01Z", 24);

		String jSon = objectMapper.writeValueAsString(request);
		HttpEntity<Object> requestEntity = getHttpEntity(jSon);
		ResponseEntity<PaymentPlanResponse> response = template.postForEntity("/generate-plan", requestEntity,
				PaymentPlanResponse.class);

		Assert.assertEquals(403, response.getStatusCode().value());
	}

	@Test
	public void generatePlan_InvalidDateFormat_ReturnsBadRequestStatusCode() throws Exception {
		PaymentPlanRequest request = new PaymentPlanRequest(BigDecimal.valueOf(5000), 5.0, "2018-01-01", 24);

		String jSon = objectMapper.writeValueAsString(request);
		HttpEntity<Object> requestEntity = getHttpEntity(jSon);
		ResponseEntity<PaymentPlanResponse> response = template.postForEntity("/generate-plan", requestEntity,
				PaymentPlanResponse.class);

		Assert.assertEquals(400, response.getStatusCode().value());
	}

	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}
}
