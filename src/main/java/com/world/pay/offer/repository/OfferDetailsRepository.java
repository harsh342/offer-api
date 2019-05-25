package com.world.pay.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.world.pay.offer.entity.OfferDetails;

@Repository
public interface OfferDetailsRepository extends JpaRepository<OfferDetails, Integer>{

}
