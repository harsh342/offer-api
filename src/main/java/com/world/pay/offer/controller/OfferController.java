package com.world.pay.offer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.world.pay.offer.bean.CreateOfferRequest;
import com.world.pay.offer.bean.CreateOfferResponse;
import com.world.pay.offer.bean.ResponseStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(path = "/world/pay/v1")
public class OfferController {

	@ApiOperation(value = "API to create offers for Merchants",
			 notes = "Valid Merchants are Merchant_1 to Merchant_9")
	@RequestMapping(path = "/offer/create", method = RequestMethod.POST)
	public @ResponseBody
		ResponseEntity<CreateOfferResponse> creatOffer(@RequestBody CreateOfferRequest createOfferRequest){
		return null;
	}
	
	@RequestMapping(path = "/offers/{offerid}/details", method = RequestMethod.GET)
	public ResponseEntity<CreateOfferResponse> getOffer(@PathVariable(name = "offerid") String offerid){
		return null;
	}
	
	@RequestMapping(path = "/offers/{offerid}", method = RequestMethod.GET)
	public ResponseEntity<ResponseStatus> deleteOffer(@PathVariable(name = "offerid") String offerid){
		return null;
	}
}
