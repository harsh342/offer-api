package com.world.pay.offer.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class OfferControllerTest {

	@InjectMocks
	private OfferController offerController;
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
	
	@Test
	public void testCreateOffer_bad_req_merchant_not_registered() {
		
	}
	
	/**
	 * Offer Start Date is later than offer end date
	 * 
	 * Return:
	 * Error Message: Bad Request
	 * Http Error Code BAD_REQUEST : 400
	 */
	
	@Test
	public void testCreateOffer_startdate_after_enddate() {
		
	}
	
	/**
	 * Offer End date is before Start date
	 * 
	 * Return:
	 * Error Message : Bad Request
	 * Http Error Code BAD_REQUEST : 400
	 * 
	 */
	
	@Test
	public void testCreateOffer_enddate_before_startdate() {
		
	}
	
	/**
	 * DB error in Offer Creation
	 * 
	 * Return:
	 * Error Message: Technical Support Error
	 * Http Error Code BAD_REQUEST : 500
	 */
	
	@Test
	public void testCreateOffer_technical_support_error() {
		
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
		
	}
	/**
	 * Offer Id
	 * 
	 * 
	 * Return: 
	 * No Record Found
	 * HTTP Code 404 : Not Found
	 */
	@Test
	public void testGetOffer_Not_Found() {
		
	}
	/**
	 * Offer Id
	 * 
	 * 
	 * Return: 
	 * HTTP Code 500 : Technical Support Error
	 */
	public void testGetOffer_tech_error() {
		
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
		
	}
	/**
	 * Offer Id 
	 * 
	 * Return: 
	 * No Record Found
	 * HTTP Code 404 : Not Found
	 * 
	 */
	@Test
	public void testDeleteOffer_record_not_found() {
		
	}
	/**
	 * Offer Id 
	 * 
	 * Return: 
	 * HTTP Code 500 : Technical Support Error
	 * 
	 */
	@Test
	public void testDeleteOffer_Error() {
		
	}
}
