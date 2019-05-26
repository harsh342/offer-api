package com.world.pay.offer.bean;

import java.time.LocalDate;

public class CreateOfferRequest {
	
	private String offerDescription;
	private String offerCurrency;
	private String offerType;
	private String merchantName;
	private LocalDate offerStartDate;
	private LocalDate offerEndDate;
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
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public LocalDate getOfferStartDate() {
		return offerStartDate;
	}
	public void setOfferStartDate(LocalDate offerStartDate) {
		this.offerStartDate = offerStartDate;
	}
	public LocalDate getOfferEndDate() {
		return offerEndDate;
	}
	public void setOfferEndDate(LocalDate offerEndDate) {
		this.offerEndDate = offerEndDate;
	}
	
	
}
