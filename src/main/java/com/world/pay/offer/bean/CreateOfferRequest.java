package com.world.pay.offer.bean;

import java.util.Date;

public class CreateOfferRequest {
	
	private String offerDescription;
	private String offerCurrency;
	private String offerType;
	private String merchantName;
	private Date offerStartDate;
	private Date offerEndDate;
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
	public Date getOfferStartDate() {
		return offerStartDate;
	}
	public void setOfferStartDate(Date offerStartDate) {
		this.offerStartDate = offerStartDate;
	}
	public Date getOfferEndDate() {
		return offerEndDate;
	}
	public void setOfferEndDate(Date offerEndDate) {
		this.offerEndDate = offerEndDate;
	}
	
	
}
