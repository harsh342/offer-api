package com.world.pay.offer.bean;

public enum Offers {
	
ACTIVE("ACTIVE"),CANCEL("CANCEL"),EXPIRE("EXPIRE");
	
	private String value;

	public String getValue() {
		return value;
	}

	private Offers(String value) {
		this.value = value;
	}
}
