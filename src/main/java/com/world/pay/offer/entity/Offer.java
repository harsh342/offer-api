package com.world.pay.offer.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OFFER")
public class Offer{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OFFER_ID")
	private Integer offerId;
	@Column(name = "OFFER_START_DATE")
	private Date offerStartDate;
	@Column(name = "OFFER_END_DATE")
	private Date offerEndDate;
	@Column(name = "STATUS")
	private int status;
	@Column(name = "CREATE_TS")
	private Timestamp createTs;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, 
			fetch = FetchType.LAZY)
	@JoinColumn(name = "OFFER_ID")
	private OfferDetails offerDetails;
	
	public Integer getOfferId() {
		return offerId;
	}
	public void setOfferId(Integer offerId) {
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
	public OfferDetails getOfferDetails() {
		return offerDetails;
	}
	public void setOfferDetails(OfferDetails offerDetails) {
		this.offerDetails = offerDetails;
	}
	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", offerStartDate=" + offerStartDate + ", offerEndDate=" + offerEndDate
				+ ", status=" + status + ", createTs=" + createTs + "]";
	}
	
	

}
