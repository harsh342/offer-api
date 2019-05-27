package com.world.pay.offer.bean;

import java.util.Arrays;
import java.util.Optional;

public enum OfferStatus {
	
	ACTIVE("ACTIVE")
	,CANCEL("CANCEL")
	,EXPIRE("EXPIRE");
	
	private String value;

	public String getValue() {
		return value;
	}

	private OfferStatus(String value) {
		this.value = value;
	}
	
	public static Optional<OfferStatus> getOfferStatus(final String offTypeCurr) {
		return Arrays.stream(OfferStatus.values()).
			filter((offcurTyp)->offcurTyp.getValue().equals(offTypeCurr)).findAny();
	}
}
