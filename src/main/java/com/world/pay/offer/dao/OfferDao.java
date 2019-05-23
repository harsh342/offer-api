package com.world.pay.offer.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.world.pay.offer.entity.Offer;
import com.world.pay.offer.repository.OfferRepository;

@Component
public class OfferDao {
	
	@Autowired
	private OfferRepository offerRepository;
	
	/**
	 * Save Offer Details in DB
	 * @param offer
	 * @return
	 */
	public Offer createOffer(final Offer offer) {
		return offerRepository.save(offer);
	}
	
	/**
	 * Fetches Offer Details from DB
	 * @param offerId
	 * @return
	 */
	public Optional<Offer> getOfferDetails(final int offerId) {
		return offerRepository.findById(offerId);
	}
	
	/**
	 * Update Offer Details in DB
	 * @param offer
	 * @return
	 */
	public Offer updateOffer(final Offer offer) {
		return offerRepository.save(offer);
	}

}
