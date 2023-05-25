package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Delivery;

public interface DeliveryRepo extends JpaRepository<Delivery, Long> {

}
