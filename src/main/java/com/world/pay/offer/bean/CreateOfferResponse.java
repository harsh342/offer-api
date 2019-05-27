package com.world.pay.offer.bean;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CreateOfferResponse extends ResponseStatus{

	private Integer offerId;
	private String offerDescription;
	private String offerCurrency;
	private String offerType;
	private String merchantName;
	private LocalDate offerStartDate;
	private LocalDate offerEndDate;
	private String status;
	private BigDecimal offerAmount;
	
	public Integer getOfferId() {
		return offerId;
	}
	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getOfferAmount() {
		return offerAmount;
	}
	public void setOfferAmount(BigDecimal offerAmount) {
		this.offerAmount = offerAmount;
	}
	
	
}
