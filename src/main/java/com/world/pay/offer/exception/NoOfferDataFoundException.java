package com.world.pay.offer.exception;

public class NoOfferDataFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoOfferDataFoundException(String message) {
		super(message);
	}

	public NoOfferDataFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	

}
