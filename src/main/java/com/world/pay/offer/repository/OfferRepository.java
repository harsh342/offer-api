package com.world.pay.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.world.pay.offer.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer>{

}
