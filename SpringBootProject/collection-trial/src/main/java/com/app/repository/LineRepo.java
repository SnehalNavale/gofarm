package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Line;

public interface LineRepo extends JpaRepository<Line, Long> {
	List<Line> findByLineName(String lineName);
}
