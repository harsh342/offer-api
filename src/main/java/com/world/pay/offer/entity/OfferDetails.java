package com.world.pay.offer.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OFFER_DETAILS")
public class OfferDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OFFER_ID")
	private Integer offerId;
	@Column(name = "MERCHANT_ID")
	private Integer merchantId;
	@Column(name = "OFFER_DESCRIPTION")
	private String offerDescription;
	@Column(name = "OFFER_CURRENCY")
	private String offerCurrency;
	@Column(name = "OFFER_TYPE")
	private String offerType;
	@Column(name = "OFFER_AMOUNT")
	private BigDecimal offerAmount;
	
	@OneToOne(mappedBy = "offerDetails",
			fetch = FetchType.LAZY, 
			cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Offer offer;

	public Integer getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	public String getOfferCurrency() {
		return offerCurrency;
	}

	public void setOfferCurrency(String offerCurrency) {
		this.offerCurrency = offerCurrency;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public BigDecimal getOfferAmount() {
		return offerAmount;
	}

	public void setOfferAmount(BigDecimal offerAmount) {
		this.offerAmount = offerAmount;
	}

}
