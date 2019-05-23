package com.world.pay.offer.exception;

import javax.persistence.NoResultException;

public class NoOfferDataFoundException extends NoResultException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoOfferDataFoundException(String message) {
		super(message);
	}

	

}
