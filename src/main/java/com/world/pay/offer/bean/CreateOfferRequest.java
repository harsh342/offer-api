package com.world.pay.offer.bean;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class CreateOfferRequest {
	
	@NotBlank(message = "Offer Description is Mandatory")
	private String offerDescription;
	@NotBlank(message = "Offer Currency is Mandatory")
	private String offerCurrency;
	@NotBlank(message = "Offer Type is Mandatory")
	private String offerType;
	@NotNull(message = "Offer Amount is Mandatory. Please provide numaric value upto two decimal places.")
	@Digits(integer = 20, fraction = 2)
	private BigDecimal offerAmount;
	@NotBlank(message = "Merchant Name is Mandatory")
	private String merchantName;
	@NotNull(message = "Offer Start Date is Mandatory in format YYYY-MM-DD.")
	@DateTimeFormat
	private LocalDate offerStartDate;
	@NotNull(message = "Offer End Date is Mandatory in format YYYY-MM-DD.")
	@DateTimeFormat
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
	public BigDecimal getOfferAmount() {
		return offerAmount;
	}
	public void setOfferAmount(BigDecimal offerAmount) {
		this.offerAmount = offerAmount;
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
	@Override
	public String toString() {
		return "CreateOfferRequest [offerDescription=" + offerDescription + ", offerCurrency=" + offerCurrency
				+ ", offerType=" + offerType + ", offerAmount=" + offerAmount + ", merchantName=" + merchantName
				+ ", offerStartDate=" + offerStartDate + ", offerEndDate=" + offerEndDate + "]";
	}
	
	
	
}
