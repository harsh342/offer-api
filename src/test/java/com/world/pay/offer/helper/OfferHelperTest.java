package com.world.pay.offer.helper;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import com.world.pay.offer.bean.CreateOfferRequest;
import com.world.pay.offer.entity.Offer;
import com.world.pay.offer.exception.OfferDataException;

@RunWith(PowerMockRunner.class)
public class OfferHelperTest {
	
	@InjectMocks
	private OfferHelper offerHelper;
	/**
	 * Offer Request
	 * Validate the offer request and copy properties to Offer Entity
	 * 
	 * Return: OfferEntity
	 *
	 */
	@Test
	public void testCreateOfferEntity_vaid_values() throws OfferDataException {
		CreateOfferRequest  createOfferRequest
		 = new CreateOfferRequest();
		createOfferRequest.setMerchantName("Merchant_1");
		createOfferRequest.setOfferCurrency("GBP");
		createOfferRequest.setOfferDescription("Cash Back Offer");
		createOfferRequest.setOfferStartDate(LocalDate.now());
		createOfferRequest.setOfferEndDate(LocalDate.now().plusDays(10));
		createOfferRequest.setOfferType("Cash_Back");
		Offer offer = offerHelper.createOfferEntity(createOfferRequest);
	
		assertEquals("GBP", offer.getOfferDetails().getOfferCurrency());
	}
	
	/**
	 * Offer Request with un-registered merchants name
	 * 
	 * Throw OfferDataException
	 */
	
	@Test(expected = OfferDataException.class)
	public void testCreateOfferEntity_un_registered_merchant() throws OfferDataException {
		CreateOfferRequest  createOfferRequest
		 = new CreateOfferRequest();
		createOfferRequest.setMerchantName("Merchant_not_registered");
		createOfferRequest.setOfferCurrency("GBP");
		createOfferRequest.setOfferDescription("Cash Back Offer");
		createOfferRequest.setOfferStartDate(LocalDate.now());
		createOfferRequest.setOfferEndDate(LocalDate.now().plusDays(10));
		createOfferRequest.setOfferType("Cash_Back");
		offerHelper.createOfferEntity(createOfferRequest);
	}

}
