package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.DeliveryBoy;

public interface DeliveryBoyRepo extends JpaRepository<DeliveryBoy, Long> {
	Optional<DeliveryBoy> findByEmail(String email);
	boolean existsByEmail(String email);

}
