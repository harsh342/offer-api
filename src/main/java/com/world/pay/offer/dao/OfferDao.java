package com.world.pay.offer.dao;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.world.pay.offer.bean.OfferStatus;
import com.world.pay.offer.entity.Offer;
import com.world.pay.offer.exception.NoOfferDataFoundException;
import com.world.pay.offer.exception.OfferDataException;
import com.world.pay.offer.repository.OfferRepository;

@Component
public class OfferDao {

	@Autowired
	private OfferRepository offerRepository;

	/**
	 * Save Offer Details in DB
	 * 
	 * @param offer
	 * @return
	 */
	public Offer createOffer(final Offer offer) {
		try {
			return offerRepository.save(offer);
		} catch (OfferDataException e) {
			throw new OfferDataException(
					"Error Saving Offer Data for MERCHANT" + "=" + offer.getOfferDetails().getMerchantId(), e);
		}
	}

	/**
	 * Fetches Offer Details from DB
	 * 
	 * @param offerId
	 * @return
	 */
	public Offer getActiveOfferDetails(final int offerId) {

		Offer offer = offerRepository.findByOfferIdAndStatus(offerId, OfferStatus.ACTIVE.getValue(),
				Date.valueOf(LocalDate.now()));

		if (offer == null) {
			throw new NoOfferDataFoundException("No Active Results found for OFFER_ID= " + offerId);
		}

		return offer;
	}

	/**
	 * Update Offer Status in DB in DB
	 * 
	 * @param offer
	 * @return
	 */
	public int updateOffer(final String status, final int offerId) {
		int recordsUpdated = 0;
		try {
			recordsUpdated =  offerRepository.updateOfferStatus(status, offerId);
			if (recordsUpdated == 0) {
				throw new NoOfferDataFoundException("No Record found for OFFER_ID= " + offerId);
			}
		} catch (OfferDataException e) {
			throw new OfferDataException("Error Updating Offer Data for OfferId=" + offerId, e);
		}
		return recordsUpdated;
	}

}
