package com.world.pay.offer.controller;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.world.pay.offer.bean.CreateOfferRequest;
import com.world.pay.offer.bean.CreateOfferResponse;
import com.world.pay.offer.bean.OfferStatus;
import com.world.pay.offer.bean.ResponseStatus;
import com.world.pay.offer.dao.OfferDao;
import com.world.pay.offer.entity.Offer;
import com.world.pay.offer.exception.NoOfferDataFoundException;
import com.world.pay.offer.exception.OfferDataException;
import com.world.pay.offer.helper.OfferHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(path = "/world/pay/v1")
public class OfferController {
	
	Logger logger = LoggerFactory.getLogger(OfferController.class);
	 

	@Autowired
	private OfferHelper offerHelper;
	@Autowired
	private OfferDao offerDao;

	@ApiOperation(value = "API to create offers for Merchants", notes = "Valid Merchants are Merchant_1 to Merchant_9")
	@RequestMapping(path = "/offer/create", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<CreateOfferResponse> creatOffer(
			@Valid @RequestBody CreateOfferRequest createOfferRequest) {

		Offer offer = null;
		CreateOfferResponse createOfferResponse = null;
		ResponseEntity<CreateOfferResponse> responseEntity = null;

		try {

			offerHelper.validateRequest(createOfferRequest);

			offer = offerHelper.createOfferEntity(createOfferRequest);

			offerDao.createOffer(offer);

			createOfferResponse = offerHelper.createOfferReponse(offer);

			createOfferResponse.setErrorCode("201");
			createOfferResponse.setResponseMessage("Offer Created Successfully");

			responseEntity = new ResponseEntity<>(createOfferResponse, HttpStatus.CREATED);

		} catch (OfferDataException e) {
			createOfferResponse = new CreateOfferResponse();
			if(e.getCause() instanceof PersistenceException) {
				createOfferResponse.setErrorCode("500");
				createOfferResponse.setResponseMessage("Contact Technical Support Team.");
				responseEntity = new ResponseEntity<>(createOfferResponse, HttpStatus.INTERNAL_SERVER_ERROR);
				logger.debug("OFFER_CREATION_ERROR"+createOfferRequest.toString(), e);
			}else {
				createOfferResponse.setErrorCode("400");
				createOfferResponse.setResponseMessage(e.getMessage());
				responseEntity = new ResponseEntity<>(createOfferResponse, HttpStatus.BAD_REQUEST);
			}
			
		}
		return responseEntity;
	}

	@RequestMapping(path = "/offers/{offerid}", method = RequestMethod.GET)
	public ResponseEntity<CreateOfferResponse> getOffer(@PathVariable(name = "offerid") Integer offerid) {
		ResponseEntity<CreateOfferResponse> responseEntity = null;
		CreateOfferResponse createOfferResponse = null;
		
		try {
		
		createOfferResponse = offerHelper.createOfferReponse(offerDao.getActiveOfferDetails(offerid));
		responseEntity = new ResponseEntity<>(createOfferResponse, HttpStatus.OK);
		
		createOfferResponse.setErrorCode("200");
		createOfferResponse.setResponseMessage("Active Offer Found");
		
		}catch(NoOfferDataFoundException e) {
			createOfferResponse = new CreateOfferResponse();
				createOfferResponse.setErrorCode("404");
				createOfferResponse.setResponseMessage(e.getMessage());
				responseEntity = new ResponseEntity<>(createOfferResponse, HttpStatus.NOT_FOUND);
				logger.debug("NO_RECORD_FOUND="+offerid, e);
		}
		
		return responseEntity;
	}

	@RequestMapping(path = "/offers/{offerid}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseStatus> deleteOffer(@PathVariable(name = "offerid") Integer offerid) {
		ResponseEntity<ResponseStatus> responseEntity = null;
		ResponseStatus responseStatus = null;
		
		try {
		offerDao.updateOffer(OfferStatus.CANCEL.getValue(), offerid);
		
		responseStatus = new ResponseStatus();
		responseStatus.setResponseMessage("Offer Cancelled for Offer Id="+offerid);
		responseStatus.setErrorCode("200");
		responseEntity  = new ResponseEntity<>(responseStatus,HttpStatus.OK);
		
		}catch(NoOfferDataFoundException e) {
			responseStatus = new ResponseStatus();
			responseStatus.setResponseMessage(e.getMessage());
			responseStatus.setErrorCode("400");
			responseEntity  = new ResponseEntity<>(responseStatus,HttpStatus.BAD_REQUEST);
			logger.debug("NO_UPDATE_RECORD_FOUND="+offerid, e);
		}catch(OfferDataException e) {
			responseStatus = new ResponseStatus();
			responseStatus.setResponseMessage(e.getMessage());
			responseStatus.setErrorCode("500");
			responseEntity  = new ResponseEntity<>(responseStatus,HttpStatus.INTERNAL_SERVER_ERROR);
			logger.debug("OFFER_CANCEL_ERROR"+offerid, e);
		}
		
		return responseEntity;
	}
}
