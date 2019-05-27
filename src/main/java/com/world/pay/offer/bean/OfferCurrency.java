package com.world.pay.offer.bean;

import java.util.Arrays;
import java.util.Optional;

public enum OfferCurrency {

	GBP("GBP")
	,MERCHANT_POINTS("POINTS");
	
	private String value;

	public String getValue() {
		return value;
	}

	private OfferCurrency(String value) {
		this.value = value;
	}
	
	public static Optional<OfferCurrency> getOfferCurr(final String offTypeCurr) {
		return Arrays.stream(OfferCurrency.values()).
			filter((offcurTyp)->offcurTyp.getValue().equals(offTypeCurr)).findAny();
	}
	
}
