package com.world.pay.offer.helper;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
	
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	
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
	
	@Test
	public void testCreateOfferEntity_un_registered_merchant() throws OfferDataException {
		
		thrown.expect(OfferDataException.class);
		thrown.expectMessage("Merchant is not registered.");
		
		CreateOfferRequest  createOfferRequest
		 = new CreateOfferRequest();
		createOfferRequest.setMerchantName("Merchant_not_registered");
		createOfferRequest.setOfferCurrency("GBP");
		createOfferRequest.setOfferDescription("Cash Back Offer");
		createOfferRequest.setOfferStartDate(LocalDate.now());
		createOfferRequest.setOfferEndDate(LocalDate.now().plusDays(10));
		createOfferRequest.setOfferType("Cash_Back");
		createOfferRequest.setOfferAmount(BigDecimal.valueOf(100));
		offerHelper.validateRequest(createOfferRequest);
	}

	/**
	 * Incorrect Offer Type
	 * 
	 * Throw OfferDataException
	 * Message : Incorrect Offer Type.
	 */
	
	@Test
	public void testCreateOfferEntity_incorrect_offer_type() throws OfferDataException {
		
		thrown.expect(OfferDataException.class);
		thrown.expectMessage("Incorrect Offer Type.");
		
		CreateOfferRequest  createOfferRequest
		 = new CreateOfferRequest();
		createOfferRequest.setMerchantName("MERCHANT_1");
		createOfferRequest.setOfferCurrency("GBP");
		createOfferRequest.setOfferDescription("Cash Back Offer");
		createOfferRequest.setOfferStartDate(LocalDate.now());
		createOfferRequest.setOfferEndDate(LocalDate.now().plusDays(10));
		createOfferRequest.setOfferType("INCORRECT");
		createOfferRequest.setOfferAmount(BigDecimal.valueOf(100));
		offerHelper.validateRequest(createOfferRequest);
	}
	
	/**
	 * Incorrect Offer Currency
	 * 
	 * Throw OfferDataException
	 */
	
	@Test
	public void testCreateOfferEntity_incorrect_currency() throws OfferDataException {
		
		thrown.expect(OfferDataException.class);
		thrown.expectMessage("Incorrect Offer Currency.");
		
		CreateOfferRequest  createOfferRequest
		 = new CreateOfferRequest();
		createOfferRequest.setMerchantName("MERCHANT_1");
		createOfferRequest.setOfferCurrency("INR");
		createOfferRequest.setOfferDescription("Cash Back Offer");
		createOfferRequest.setOfferStartDate(LocalDate.now());
		createOfferRequest.setOfferEndDate(LocalDate.now().plusDays(10));
		createOfferRequest.setOfferType("Cash_Back");
		createOfferRequest.setOfferAmount(BigDecimal.valueOf(100));
		offerHelper.validateRequest(createOfferRequest);
	}
	
	/**
	 * From Date After to Date / To Date Before from Date
	 * 
	 * Throw OfferDataException
	 */
	
	@Test
	public void testCreateOfferEntity_from_date_after_to_date() throws OfferDataException {
		
		thrown.expect(OfferDataException.class);
		thrown.expectMessage("Offer Start Date is After Offer End Date.");
		
		CreateOfferRequest  createOfferRequest
		 = new CreateOfferRequest();
		createOfferRequest.setMerchantName("MERCHANT_1");
		createOfferRequest.setOfferCurrency("GBP");
		createOfferRequest.setOfferDescription("Cash Back Offer");
		createOfferRequest.setOfferStartDate(LocalDate.now().plusDays(10));
		createOfferRequest.setOfferEndDate(LocalDate.now());
		createOfferRequest.setOfferType("Cash_Back");
		createOfferRequest.setOfferAmount(BigDecimal.valueOf(100));
		offerHelper.validateRequest(createOfferRequest);
	}
	
	
	/**
	 * Offer Type as Discount and Offer Curr other than GBP
	 * 
	 * Throw OfferDataException
	 */
	

	@Test
	public void testCreateOfferEntity_descount_off_curr_not_GBP() throws OfferDataException {
		
		thrown.expect(OfferDataException.class);
		thrown.expectMessage("For Discount Offer Currency Must be GBP.");
		
		CreateOfferRequest  createOfferRequest
		 = new CreateOfferRequest();
		createOfferRequest.setMerchantName("MERCHANT_1");
		createOfferRequest.setOfferCurrency("POINTS");
		createOfferRequest.setOfferDescription("Cash Back Offer");
		createOfferRequest.setOfferStartDate(LocalDate.now());
		createOfferRequest.setOfferEndDate(LocalDate.now().plusDays(10));
		createOfferRequest.setOfferType("DISCOUNT");
		createOfferRequest.setOfferAmount(BigDecimal.valueOf(10.01));
		offerHelper.validateRequest(createOfferRequest);
	}
	
	/**
	 * Offer Type as Discount and Offer Currency is GBP and Offer Amount is not between 0 to 100
	 * 
	 * Throw OfferDataException
	 */
	
	@Test
	public void testCreateOfferEntity_descount_off_curr_GBP_amount_issue() throws OfferDataException {
		
		thrown.expect(OfferDataException.class);
		thrown.expectMessage("For Discount offers amount must be between 0 and 100");
		
		CreateOfferRequest  createOfferRequest
		 = new CreateOfferRequest();
		createOfferRequest.setMerchantName("MERCHANT_1");
		createOfferRequest.setOfferCurrency("GBP");
		createOfferRequest.setOfferDescription("Cash Back Offer");
		createOfferRequest.setOfferStartDate(LocalDate.now());
		createOfferRequest.setOfferEndDate(LocalDate.now().plusDays(10));
		createOfferRequest.setOfferType("DISCOUNT");
		createOfferRequest.setOfferAmount(BigDecimal.valueOf(100.10));
		offerHelper.validateRequest(createOfferRequest);
	}
}
