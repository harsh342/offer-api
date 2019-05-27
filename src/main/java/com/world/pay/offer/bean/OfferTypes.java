package com.world.pay.offer.bean;

import java.util.Arrays;
import java.util.Optional;

public enum OfferTypes {
	
	CASH_BACK("CASH_BACK")
	,DISCOUNT_PERCENT("DISCOUNT")
	,REWARD_POINTS("POINTS");
	
	private String value;

	public String getValue() {
		return value;
	}

	private OfferTypes(String value) {
		this.value = value;
	}
	
	public static Optional<OfferTypes> getOfferTy(final String offTypeCurr) {
		return Arrays.stream(OfferTypes.values()).
			filter((offcurTyp)->offcurTyp.getValue().equals(offTypeCurr)).findAny();
	}

}
