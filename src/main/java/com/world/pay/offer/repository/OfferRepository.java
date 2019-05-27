package com.world.pay.offer.repository;

import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.world.pay.offer.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer>{
	
	@Query("select off from Offer off where off.offerId =:offerId and off.status =:status and off.offerEndDate >=:offerEndDate")
	Offer findByOfferIdAndStatus(int offerId, String status, Date offerEndDate);
	
	@Transactional
	@Modifying
	@Query("update Offer off set off.status =:status where off.offerId =:offerId")
	int updateOfferStatus(@Param("status") String status,@Param("offerId") int offerId);

}
