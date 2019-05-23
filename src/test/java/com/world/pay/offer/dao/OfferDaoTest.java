package com.world.pay.offer.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.world.pay.offer.entity.Offer;
import com.world.pay.offer.exception.NoOfferDataFoundException;
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
	 * Throw PersistenceException
	 */
	@Test(expected = PersistenceException.class)
	public void testOfferCreateFailed_DBerror() {
		
		Offer offer = new Offer();
		offer.setOfferStartDate(Date.valueOf(LocalDateTime.now().atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setOfferEndDate(Date.valueOf(LocalDateTime.now().plusDays(1).atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setCreateTs(Timestamp.from(LocalDateTime.now().atZone(ZoneId.of("Z")).toInstant()));
		
		PowerMockito.when(offerRepository.save(offer)).thenThrow(PersistenceException.class);
		
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
		
		PowerMockito.when(offerRepository.findById(1)).thenReturn(Optional.ofNullable(offer));
		
		Optional<Offer> offReturn = offerDao.getOfferDetails(1);
		
		Assert.assertEquals(1, offReturn.get().getOfferId());
		
	}
	
	/**
	 * Fetch Details with offer id
	 * input offer id
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
		
		PowerMockito.when(offerRepository.findById(1)).thenThrow(NoOfferDataFoundException.class);
		
		offerDao.getOfferDetails(1);
	}
	
	/**
	 * Update the offer details
	 * Input :
	 * Offer Start Date 
	 * Offer End Date
	 * status
	 * Create TS
	 * 
	 * Return updated offer bean
	 * 
	 */
	@Test
	public void updateOffer_ok() {
		
		Offer offer = new Offer();
		offer.setOfferStartDate(Date.valueOf(LocalDateTime.now().atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setOfferEndDate(Date.valueOf(LocalDateTime.now().plusDays(1).atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setCreateTs(Timestamp.from(LocalDateTime.now().atZone(ZoneId.of("Z")).toInstant()));
		offer.setStatus(1);
		
		PowerMockito.when(offerRepository.save(offer)).thenReturn(offer);
		
		offerDao.updateOffer(offer);
		
		Assert.assertEquals(offer, offer);
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
		
		Offer offer = new Offer();
		offer.setOfferStartDate(Date.valueOf(LocalDateTime.now().atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setOfferEndDate(Date.valueOf(LocalDateTime.now().plusDays(1).atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setCreateTs(Timestamp.from(LocalDateTime.now().atZone(ZoneId.of("Z")).toInstant()));
		
		PowerMockito.when(offerRepository.save(offer)).thenThrow(NoOfferDataFoundException.class);
		
		offerDao.createOffer(offer);
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
	@Test(expected = PersistenceException.class)
	public void updateOffer_persistance_error() {
		
		Offer offer = new Offer();
		offer.setOfferStartDate(Date.valueOf(LocalDateTime.now().atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setOfferEndDate(Date.valueOf(LocalDateTime.now().plusDays(1).atZone(ZoneId.of("Z")).toLocalDate()));
		offer.setCreateTs(Timestamp.from(LocalDateTime.now().atZone(ZoneId.of("Z")).toInstant()));
		
		PowerMockito.when(offerRepository.save(offer)).thenThrow(PersistenceException.class);
		
		offerDao.createOffer(offer);
	}
}
