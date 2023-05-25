package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Line;
import com.app.entities.LineOrder;
import com.app.entities.Status;

public interface LineOrderRepo extends JpaRepository<LineOrder, Long> {
	List<LineOrder> findByStatus(Status status);
	List<LineOrder> findByLineNo(Line lineNo);
}
