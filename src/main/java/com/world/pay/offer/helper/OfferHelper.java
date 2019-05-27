package com.world.pay.offer.helper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.world.pay.offer.bean.CreateOfferRequest;
import com.world.pay.offer.bean.CreateOfferResponse;
import com.world.pay.offer.bean.Merchants;
import com.world.pay.offer.bean.OfferCurrency;
import com.world.pay.offer.bean.OfferStatus;
import com.world.pay.offer.bean.OfferTypes;
import com.world.pay.offer.entity.Offer;
import com.world.pay.offer.entity.OfferDetails;
import com.world.pay.offer.exception.OfferDataException;

@Component
public class OfferHelper {

	/**
	 * Method Create Offer Entity to be saved in DB
	 * 
	 * @param createOfferRequest
	 * @return
	 * @throws OfferDataException
	 */
	public Offer createOfferEntity(final CreateOfferRequest createOfferRequest){
		Offer offer = new Offer();

		offer.setOfferStartDate(Date.valueOf(createOfferRequest.getOfferStartDate()));
		offer.setOfferEndDate(Date.valueOf(createOfferRequest.getOfferEndDate()));
		offer.setCreateTs(Timestamp.valueOf(LocalDateTime.now()));
		offer.setStatus(OfferStatus.ACTIVE.getValue());

		OfferDetails offerDetails = new OfferDetails();
		offerDetails.setOfferAmount(createOfferRequest.getOfferAmount());
		offerDetails.setOfferCurrency(createOfferRequest.getOfferCurrency().toUpperCase());
		offerDetails.setOfferType(createOfferRequest.getOfferType().toUpperCase());
		offerDetails.setOfferDescription(createOfferRequest.getOfferDescription());
		offerDetails.setMerchantId(
				Merchants.getMerchantId(createOfferRequest.getMerchantName().toUpperCase()).get().getMerchantId());
		
		offerDetails.setOffer(offer);

		offer.setOfferDetails(offerDetails);

		return offer;
	}

	/**
	 * Method Validates the Request
	 * @param createOfferRequest
	 * @throws OfferDataException
	 */
	public void validateRequest(final CreateOfferRequest createOfferRequest){

		if (createOfferRequest.getOfferStartDate().isAfter(createOfferRequest.getOfferEndDate())) {
			throw new OfferDataException("Offer Start Date is After Offer End Date.");
		} else if (!OfferTypes.getOfferTy(createOfferRequest.getOfferType().toUpperCase()).isPresent()) {
			throw new OfferDataException("Incorrect Offer Type.");
		} else if (!OfferCurrency.getOfferCurr(createOfferRequest.getOfferCurrency().toUpperCase()).isPresent()) {
			throw new OfferDataException("Incorrect Offer Currency.");
		}  else if (!Merchants.getMerchantId(createOfferRequest.getMerchantName().toUpperCase()).isPresent()) {
			throw new OfferDataException("Merchant is not registered.");
		} else if (OfferTypes.DISCOUNT_PERCENT.getValue().equalsIgnoreCase(createOfferRequest.getOfferType())
				&& !OfferCurrency.GBP.getValue().equalsIgnoreCase(createOfferRequest.getOfferCurrency())) {
			throw new OfferDataException("For Discount Offer Currency Must be GBP.");
		} else if (OfferTypes.DISCOUNT_PERCENT.getValue().equalsIgnoreCase(createOfferRequest.getOfferType())
				&& OfferCurrency.GBP.getValue().equalsIgnoreCase(createOfferRequest.getOfferCurrency())
				&& (createOfferRequest.getOfferAmount().compareTo(BigDecimal.valueOf(100)) > 0
						|| createOfferRequest.getOfferAmount().compareTo(BigDecimal.valueOf(0)) < 0)) {
			throw new OfferDataException("For Discount offers amount must be between 0 and 100");
		}

	}

	/**
	 * Method creates Create Offer API Response Bean CreateOfferResponse
	 * 
	 * @param offer
	 * @return
	 */
	public CreateOfferResponse createOfferReponse(final Offer offer) {
		CreateOfferResponse offerResponse = new CreateOfferResponse();
		BeanUtils.copyProperties(offer.getOfferDetails(), offerResponse);
		offerResponse.setOfferId(offer.getOfferId());
		offerResponse.setOfferStartDate(offer.getOfferStartDate().toLocalDate());
		offerResponse.setOfferEndDate(offer.getOfferEndDate().toLocalDate());
		offerResponse.setStatus(offer.getStatus());
		return offerResponse;
	}
}
