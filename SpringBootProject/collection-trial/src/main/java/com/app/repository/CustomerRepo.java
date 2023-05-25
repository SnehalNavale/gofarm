package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Customer;
import com.app.entities.DeliveryBoy;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
	Optional<Customer> findByEmail(String email);
	boolean existsByEmail(String email);
}
