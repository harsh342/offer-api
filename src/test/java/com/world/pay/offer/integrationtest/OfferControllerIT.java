package com.world.pay.offer.integrationtest;

import static org.hamcrest.CoreMatchers.is;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.world.pay.offer.bean.CreateOfferRequest;
import com.world.pay.offer.bean.CreateOfferResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OfferControllerIT {

	@Autowired
	private WebTestClient webTestClinet;
	
	@Before
	public void init() {
		
	}
	/**
	 * Test Create Offer
	 * 
	 * Response :
	 * 
	 * Response Error Code : 201 Response Message : Offer Created Successfully
	 */
	@Test
	public void offerApiRegression() {

		CreateOfferRequest createOfferRequest = new CreateOfferRequest();
		createOfferRequest.setMerchantName("Merchant_1");
		createOfferRequest.setOfferCurrency("GBP");
		createOfferRequest.setOfferDescription("Cash Back Offer");
		createOfferRequest.setOfferStartDate(LocalDate.now());
		createOfferRequest.setOfferEndDate(LocalDate.now().plusDays(10));
		createOfferRequest.setOfferType("Cash_Back");
		createOfferRequest.setOfferAmount(BigDecimal.valueOf(100.12));

		CreateOfferResponse createResponse = webTestClinet.post().uri("/world/pay/v1/offer/create")
				.syncBody(createOfferRequest).header("Content-Type", "application/json").exchange()
				.expectBody(CreateOfferResponse.class).returnResult().getResponseBody();

		Assert.assertThat(createResponse.getErrorCode(), is("201"));
		Assert.assertThat(createResponse.getResponseMessage(), is("Offer Created Successfully"));
		
		/**
		 * Test Read Active Offer from DB
		 * 
		 * Response:
		 * 
		 * HTTP Status : 200 OK
		 */
		
		webTestClinet.get().uri("/world/pay/v1/offers/{offerId}", createResponse.getOfferId()).exchange().expectStatus().isOk();
		
		/**
		 * Cancel the Offer
		 * 
		 * Response:
		 * 
		 * HTTP Status : 200 OK
		 * 
		 */
		webTestClinet.delete().uri("/world/pay/v1/offers/{offerId}", createResponse.getOfferId()).exchange().expectStatus().isOk();

	}

	
	
	
	/**
	 * Test Read non existent Offer from DB
	 * 
	 * Response:
	 * 
	 * HTTP Status : 404 NOTFOUND
	 */
	@Test
	public void btestGetOffer_NOTFOUND() {
		webTestClinet.get().uri("/world/pay/v1/offers/{offerId}", 0).exchange().expectStatus().isNotFound();
	}
	
	
	/**
	 * Cancel non existent Offer
	 * 
	 * Response:
	 * 
	 * HTTP Status : 404 NOTFOUND
	 * 
	 */
	@Test
	public void cancelOrder_NOTFOUND() {
		webTestClinet.delete().uri("/world/pay/v1/offers/{offerId}", 0).exchange().expectStatus().isNotFound();
	}
	
}
