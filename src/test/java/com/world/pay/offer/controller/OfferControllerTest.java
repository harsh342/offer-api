package com.world.pay.offer.controller;

import static org.hamcrest.CoreMatchers.is;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.PersistenceException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import com.world.pay.offer.bean.CreateOfferRequest;
import com.world.pay.offer.bean.CreateOfferResponse;
import com.world.pay.offer.bean.ResponseStatus;
import com.world.pay.offer.dao.OfferDao;
import com.world.pay.offer.entity.Offer;
import com.world.pay.offer.entity.OfferDetails;
import com.world.pay.offer.exception.NoOfferDataFoundException;
import com.world.pay.offer.exception.OfferDataException;
import com.world.pay.offer.helper.OfferHelper;

@RunWith(PowerMockRunner.class)
public class OfferControllerTest {

	@InjectMocks
	private OfferController offerController;
	
	@Spy
	private OfferHelper offerHelper = new OfferHelper();
	@Mock
	private OfferDao dao;
	
	/**
	 * Request to create Offer Record
	 * Offer Start date
	 * Offer End Date 
	 * Offer Details
	 * 
	 * Return:
	 * Offer Id
	 * Offer Details 
	 * 
	 * HTTP Code 201 : Created
	 */
	
	@Test
	public void testCreateOffer_ok() {
		
		CreateOfferRequest  createOfferRequest
		 = new CreateOfferRequest();
		createOfferRequest.setMerchantName("Merchant_1");
		createOfferRequest.setOfferCurrency("GBP");
		createOfferRequest.setOfferDescription("Cash Back Offer");
		createOfferRequest.setOfferStartDate(LocalDate.now());
		createOfferRequest.setOfferEndDate(LocalDate.now().plusDays(10));
		createOfferRequest.setOfferType("Cash_Back");
		createOfferRequest.setOfferAmount(BigDecimal.valueOf(100.12));
		
		Offer offer = new Offer();
		offer.setOfferId(1);
		OfferDetails offerDetails = new OfferDetails();
		offerDetails.setOfferAmount(BigDecimal.valueOf(100.00));
		offer.setOfferDetails(offerDetails);
		
		PowerMockito.when(dao.createOffer(Mockito.any(Offer.class))).thenReturn(offer);
		
		ResponseEntity<CreateOfferResponse> response = 
				offerController.creatOffer(createOfferRequest);
		
		Assert.assertThat(response.getBody().getOfferAmount(), is(BigDecimal.valueOf(100.12)));
	}
	
	/**
	 * Request to create Offer Record
	 * Offer Start date
	 * Offer End Date 
	 * Offer Details
	 * 
	 * Return:
	 * Error Message: Bad Request Merchant not registered
	 * Http Error Code BAD_REQUEST : 400
	 */
	
	@Test()
	public void testCreateOffer_bad_req_merchant_not_registered() {
		
		CreateOfferRequest  createOfferRequest
		 = new CreateOfferRequest();
		createOfferRequest.setMerchantName("Merchant_Not_Registered");
		createOfferRequest.setOfferCurrency("GBP");
		createOfferRequest.setOfferDescription("Cash Back Offer");
		createOfferRequest.setOfferStartDate(LocalDate.now());
		createOfferRequest.setOfferEndDate(LocalDate.now().plusDays(10));
		createOfferRequest.setOfferType("CASH_BACK");
		createOfferRequest.setOfferAmount(BigDecimal.valueOf(100.12));
		
		Offer offer = new Offer();
		offer.setOfferId(1);
		OfferDetails offerDetails = new OfferDetails();
		offerDetails.setOfferAmount(BigDecimal.valueOf(100.00));
		offer.setOfferDetails(offerDetails);
		
		PowerMockito.when(dao.createOffer(Mockito.any(Offer.class))).thenReturn(offer);
		
		ResponseEntity<CreateOfferResponse> response = 
					offerController.creatOffer(createOfferRequest);
		Assert.assertThat(response.getBody().getErrorCode(),is("400"));
		Assert.assertThat(response.getBody().getResponseMessage(),is("Merchant is not registered."));
	}
	
	/**
	 * Offer Start Date is later than offer end date/Offer End date is before Start date
	 * 
	 * Return:
	 * Error Message: Bad Request
	 * Http Error Code BAD_REQUEST : 400
	 */
	
	@Test
	public void testCreateOffer_startdate_after_enddate() {
		
		CreateOfferRequest  createOfferRequest
		 = new CreateOfferRequest();
		createOfferRequest.setMerchantName("Merchant_1");
		createOfferRequest.setOfferCurrency("GBP");
		createOfferRequest.setOfferDescription("Cash Back Offer");
		createOfferRequest.setOfferStartDate(LocalDate.now().plusDays(10));
		createOfferRequest.setOfferEndDate(LocalDate.now());
		createOfferRequest.setOfferType("Cash_Back");
		createOfferRequest.setOfferAmount(BigDecimal.valueOf(100.12));
		
		Offer offer = new Offer();
		offer.setOfferId(1);
		OfferDetails offerDetails = new OfferDetails();
		offerDetails.setOfferAmount(BigDecimal.valueOf(100.00));
		offer.setOfferDetails(offerDetails);
		
		PowerMockito.when(dao.createOffer(Mockito.any(Offer.class))).thenReturn(offer);
		
		ResponseEntity<CreateOfferResponse> response = 
				offerController.creatOffer(createOfferRequest);
		
		Assert.assertThat(response.getBody().getErrorCode(),is("400"));
		Assert.assertThat(response.getBody().getResponseMessage(),is("Offer From Date is After To Date."));
	}
	
	
	/**
	 * DB error in Offer Creation
	 * 
	 * Return: OfferDataException
	 * Error Message: Technical Support Error
	 * Http Error Code : 500
	 */
	
	@Test
	public void testCreateOffer_technical_support_error() {
		CreateOfferRequest  createOfferRequest
		 = new CreateOfferRequest();
		createOfferRequest.setMerchantName("Merchant_1");
		createOfferRequest.setOfferCurrency("GBP");
		createOfferRequest.setOfferDescription("Cash Back Offer");
		createOfferRequest.setOfferStartDate(LocalDate.now());
		createOfferRequest.setOfferEndDate(LocalDate.now().plusDays(10));
		createOfferRequest.setOfferType("Cash_Back");
		createOfferRequest.setOfferAmount(BigDecimal.valueOf(100.12));
		
		Offer offer = new Offer();
		offer.setOfferId(1);
		OfferDetails offerDetails = new OfferDetails();
		offerDetails.setOfferAmount(BigDecimal.valueOf(100.00));
		offer.setOfferDetails(offerDetails);
		
		PowerMockito.when(dao.createOffer(Mockito.any(Offer.class))).thenThrow(new OfferDataException("Save Failed",new PersistenceException()));
		
		ResponseEntity<CreateOfferResponse> response = 
				offerController.creatOffer(createOfferRequest);
		
		Assert.assertThat(response.getBody().getErrorCode(), is("500"));
		Assert.assertThat(response.getBody().getResponseMessage(), is("Contact Technical Support Team."));
	}
	
	/**
	 * Offer Id
	 * 
	 * 
	 * Return: 
	 * Offer Details
	 * HTTP Code 200 : OK
	 */
	@Test
	public void testGetOffer_ok() {
		Offer offer = new Offer();
		offer.setOfferId(1);
		OfferDetails offerDetails = new OfferDetails();
		offerDetails.setOfferAmount(BigDecimal.valueOf(100.00));
		offer.setOfferDetails(offerDetails);
		
		PowerMockito.when(dao.getActiveOfferDetails(1)).thenReturn(offer);
		
		ResponseEntity<CreateOfferResponse>  response = 
										offerController.getOffer(1);
		
		Assert.assertThat(response.getBody().getErrorCode(), is("200"));
	}
	/**
	 * Offer Id
	 * 
	 * 
	 * Return: 
	 * No Record Found
	 * HTTP Code 404 : Not Found
	 */
	@Test(expected = NoOfferDataFoundException.class)
	public void testGetOffer_Not_Found() {
		
		PowerMockito.when(dao.getActiveOfferDetails(1)).thenThrow(NoOfferDataFoundException.class);
		
		offerController.getOffer(1);
		
	}
	/**
	 * Offer Id
	 * 
	 * 
	 * Return: 
	 * HTTP Code 500 : Technical Support Error
	 */
	public void testGetOffer_tech_error() {
		
		PowerMockito.when(dao.getActiveOfferDetails(1)).thenThrow(NoOfferDataFoundException.class);
		
		offerController.getOffer(1);
	}
	
	/**
	 * Offer Id 
	 * 
	 * Return:
	 * Offer Deleted
	 * 
	 * HTTP Code 200 : OK
	 * 
	 */
	@Test
	public void testDeleteOffer_ok() {
		
		PowerMockito.when(dao.updateOffer("CANCEL",1)).thenReturn(1);
		
		ResponseEntity<ResponseStatus> response = offerController.deleteOffer(1);
		
		Assert.assertThat(response.getBody().getErrorCode(),is("200"));
		
	}
	/**
	 * Offer Id 
	 * 
	 * Return: 
	 * No Record Found
	 * HTTP Code 404 : Not Found
	 * 
	 */
	@Test(expected = NoOfferDataFoundException.class)
	public void testDeleteOffer_record_not_found() {
		
		PowerMockito.when(dao.updateOffer("CANCEL",1)).thenThrow(NoOfferDataFoundException.class);
		
		offerController.deleteOffer(1);
	}
	/**
	 * Offer Id 
	 * 
	 * Return: 
	 * HTTP Code 500 : Technical Support Error
	 * 
	 */
	@Test(expected = OfferDataException.class)
	public void testDeleteOffer_Error() {
		PowerMockito.when(dao.updateOffer("CANCEL",1)).thenThrow(OfferDataException.class);
		
		offerController.deleteOffer(1);
	}
}
