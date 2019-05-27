package com.world.pay.offer.dao;

import static org.hamcrest.CoreMatchers.is;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.world.pay.offer.bean.OfferStatus;
import com.world.pay.offer.entity.Offer;
import com.world.pay.offer.entity.OfferDetails;
import com.world.pay.offer.exception.NoOfferDataFoundException;
import com.world.pay.offer.exception.OfferDataException;
import com.world.pay.offer.repository.OfferRepository;

@RunWith(PowerMockRunner.class)
public class OfferDaoTest {
	
	@InjectMocks
	private OfferDao offerDao;
	
	@Mock
	private OfferRepository offerRepository;
	
	@Before
	public void init() {
		
	}
	
	/**
	 * Create Offer with Required Details
	 * Offer Start Date 
	 * Offer End Date
	 * status
	 * Create TS
	 * 
	 * Return OfferId
	 */
	@Test
	public void testCreateOffer_ok() {
		
		Offer offer = new Offer();
		offer.setOfferStartDate(Date.valueOf(LocalDateTime.now().atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setOfferEndDate(Date.valueOf(LocalDateTime.now().plusDays(1).atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setCreateTs(Timestamp.from(LocalDateTime.now().atZone(ZoneId.of("Z")).toInstant()));
		
		PowerMockito.when(offerRepository.save(offer)).thenReturn(offer);
		
		offerDao.createOffer(offer);
		
		Assert.assertEquals(offer, offer);
		
	}
	
	/**
	 * Create Offer with Required Details
	 * Offer Start Date 
	 * Offer End Date
	 * status
	 * Create TS
	 * 
	 * Throw OfferDataException
	 */
	@Test(expected = OfferDataException.class)
	public void testOfferCreateFailed_DBerror() {
		
		Offer offer = new Offer();
		offer.setOfferStartDate(Date.valueOf(LocalDateTime.now().atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setOfferEndDate(Date.valueOf(LocalDateTime.now().plusDays(1).atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setCreateTs(Timestamp.from(LocalDateTime.now().atZone(ZoneId.of("Z")).toInstant()));
		OfferDetails offerDetails = new OfferDetails();
		offerDetails.setMerchantId(1);
		offer.setOfferDetails(offerDetails);
		
		PowerMockito.when(offerRepository.save(offer)).thenThrow(OfferDataException.class);
		
		offerDao.createOffer(offer);
	}
	
	
	/**
	 * Fetch Details with offer id
	 * input offer id
	 * 
	 * return offer details
	 * 
	 */

	@Test
	public void getOffer_ok() {
		
		Offer offer = new Offer();
		offer.setOfferStartDate(Date.valueOf(LocalDateTime.now().atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setOfferEndDate(Date.valueOf(LocalDateTime.now().plusDays(1).atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setCreateTs(Timestamp.from(LocalDateTime.now().atZone(ZoneId.of("Z")).toInstant()));
		offer.setOfferId(1);
		
		PowerMockito.when(offerRepository.findByOfferIdAndStatus(1,"ACTIVE",Date.valueOf(LocalDate.now()))).thenReturn(offer);
		
		Offer offReturn = offerDao.getActiveOfferDetails(1);
		
		Assert.assertEquals(new Integer(1), offReturn.getOfferId());
		
	}
	
	/**
	 * Fetch Details with offer id
	 * input offer id not found or is not in ACTIVE status or is expired
	 * 
	 * throws NoOfferDataFoundException
	 * 
	 */
	@Test(expected = NoOfferDataFoundException.class)
	public void offerId_not_found() {
		Offer offer = new Offer();
		offer.setOfferStartDate(Date.valueOf(LocalDateTime.now().atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setOfferEndDate(Date.valueOf(LocalDateTime.now().plusDays(1).atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setCreateTs(Timestamp.from(LocalDateTime.now().atZone(ZoneId.of("Z")).toInstant()));
		offer.setOfferId(1);
		OfferDetails offerDetails = new OfferDetails();
		offerDetails.setMerchantId(1);
		offer.setOfferDetails(offerDetails);
		
		PowerMockito.when(offerRepository.findByOfferIdAndStatus(1,"ACTIVE",Date.valueOf(LocalDate.now()))).thenThrow(NoOfferDataFoundException.class);
		
		offerDao.getActiveOfferDetails(1);
	}
	
	
	/**
	 * Update the offer details to cancel
	 * Input :
	 * offerId 
	 * 
	 * Return updated offer bean
	 * 
	 */
	@Test
	public void updateOffer_ok() {
		
		PowerMockito.when(offerRepository.updateOfferStatus(OfferStatus.CANCEL.getValue(), 1)).thenReturn(1);
		
		int offerId = offerDao.updateOffer(OfferStatus.CANCEL.getValue(), 1);
		
		Assert.assertThat(offerId, is(1));
	}
	
	/**
	 * Record for update not found
	 * Offer Start Date 
	 * Offer End Date
	 * status
	 * Create TS
	 * 
	 * throws NoOfferDataFoundException 
	 */
	@Test(expected = NoOfferDataFoundException.class)
	public void updateOffer_record_not_found() {
		
		PowerMockito.when(offerRepository.updateOfferStatus(OfferStatus.CANCEL.getValue(), 1)).thenThrow(NoOfferDataFoundException.class);
		
		offerDao.updateOffer(OfferStatus.CANCEL.getValue(), 1);
	}
	
	/**
	 * Record for update not found
	 * Offer Start Date 
	 * Offer End Date
	 * status
	 * Create TS
	 * 
	 * throws NoOfferDataFoundException 
	 */
	@Test(expected = OfferDataException.class)
	public void updateOffer_persistance_error() {
				
		PowerMockito.when(offerRepository.updateOfferStatus(OfferStatus.CANCEL.getValue(), 1)).thenThrow(com.world.pay.offer.exception.OfferDataException.class);
		
		offerDao.updateOffer(OfferStatus.CANCEL.getValue(), 1);
	}
}
