package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Withdraw;

public interface WithdrawRepo extends JpaRepository<Withdraw, Long> {
	List<Withdraw> findByIsValid(boolean isValid);
}
