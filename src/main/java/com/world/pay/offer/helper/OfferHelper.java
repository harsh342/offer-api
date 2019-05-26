package com.world.pay.offer.helper;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.world.pay.offer.bean.CreateOfferRequest;
import com.world.pay.offer.bean.Merchants;
import com.world.pay.offer.bean.Offers;
import com.world.pay.offer.entity.Offer;
import com.world.pay.offer.entity.OfferDetails;
import com.world.pay.offer.exception.OfferDataException;

@Component
public class OfferHelper {

	public Offer createOfferEntity(final CreateOfferRequest createOfferRequest) throws OfferDataException {
		Offer offer = new Offer();
		
		offer.setOfferStartDate(Date.valueOf(createOfferRequest.getOfferStartDate()));
		offer.setOfferEndDate(Date.valueOf(createOfferRequest.getOfferEndDate()));
		offer.setCreateTs(Timestamp.valueOf(LocalDateTime.now()));
		offer.setStatus(Offers.ACTIVE.getValue());
		
		OfferDetails offerDetails = new OfferDetails();
		BeanUtils.copyProperties(createOfferRequest, offerDetails);
		offerDetails.setMerchantId(Merchants.getMerchantId(createOfferRequest.getMerchantName().toUpperCase()));
		offerDetails.setOffer(offer);
		
		offer.setOfferDetails(offerDetails);
		
		return offer;
	}
}
