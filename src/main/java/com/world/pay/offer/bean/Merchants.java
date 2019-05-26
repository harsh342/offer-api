package com.world.pay.offer.bean;

import java.util.Arrays;
import java.util.Optional;

import com.world.pay.offer.exception.OfferDataException;

public enum Merchants {
	
	MERCHANT_1("MERCHANT_1",1)
	,MERCHANT_2("MERCHANT_2",2)
	,MERCHANT_3("MERCHANT_3",3)
	,MERCHANT_4("MERCHANT_4",4)
	,MERCHANT_5("MERCHANT_5",5)
	,MERCHANT_6("MERCHANT_6",6)
	,MERCHANT_7("MERCHANT_7",7)
	,MERCHANT_8("MERCHANT_8",8)
	,MERCHANT_9("MERCHANT_9",9);
	
	
	private String merchantName;
	private Integer merchantId;
	public String getMerchantName() {
		return merchantName;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	private Merchants(String merchantName, Integer merchantId) {
		this.merchantName = merchantName;
		this.merchantId = merchantId;
	}
	
	/**
	 * Method Compares the merchant name in the request and returns the merchant id
	 * @param merchantName
	 * @return
	 * @throws OfferDataException
	 */
	public static Integer getMerchantId(final String merchantName) throws OfferDataException {
		
		Optional<Merchants> merchant = 
				Arrays.stream(Merchants.values()).
					filter((p)->p.getMerchantName().equals(merchantName)).findAny();
		
		if(merchant.isPresent()) {
			return merchant.get().getMerchantId();
		}else {
			throw new OfferDataException("Merchant Name Not Registered");
		}
	}
	
	

}
