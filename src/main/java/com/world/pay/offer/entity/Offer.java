package com.world.pay.offer.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Offer{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int offerId;
	private Date offerStartDate;
	private Date offerEndDate;
	private int status;
	private Timestamp createTs;
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public Date getOfferStartDate() {
		return offerStartDate;
	}
	public void setOfferStartDate(Date offerStartDate) {
		this.offerStartDate = offerStartDate;
	}
	public Date getOfferEndDate() {
		return offerEndDate;
	}
	public void setOfferEndDate(Date offerEndDate) {
		this.offerEndDate = offerEndDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getCreateTs() {
		return createTs;
	}
	public void setCreateTs(Timestamp createTs) {
		this.createTs = createTs;
	}
	
	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", offerStartDate=" + offerStartDate + ", offerEndDate=" + offerEndDate
				+ ", status=" + status + ", createTs=" + createTs + "]";
	}
	
	

}
