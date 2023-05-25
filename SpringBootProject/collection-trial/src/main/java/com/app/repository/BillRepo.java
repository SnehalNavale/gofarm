package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Bill;
import com.app.entities.Subscription;

public interface BillRepo extends JpaRepository<Bill, Long> {
	 List<Bill> findBySubscription(Subscription sub);
}
