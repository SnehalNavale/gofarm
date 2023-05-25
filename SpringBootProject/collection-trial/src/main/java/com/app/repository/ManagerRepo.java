package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.DeliveryBoy;
import com.app.entities.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Long> {
	Optional<Manager> findByEmail(String email);
	boolean existsByEmail(String email);
}
