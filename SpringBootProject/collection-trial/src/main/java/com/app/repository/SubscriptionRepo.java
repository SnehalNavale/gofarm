package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.SubStatus;
import com.app.entities.Subscription;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
	List<Subscription> findBySubStatus(SubStatus status);
}
