package com.app.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.DeliveryBoy;
import com.app.entities.Line;
import com.app.entities.Manager;
import com.app.entities.Role;
import com.app.repository.DeliveryBoyRepo;
import com.app.repository.LineRepo;
import com.app.repository.ManagerRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class TestLine {
	
	@Autowired
	private LineRepo lineRepo;
	
	@Autowired 
	private DeliveryBoyRepo delboyRepo;
	
	@Autowired
	private ManagerRepo managerRepo;
	
	@Test
	void addLine() {
		Line line= new Line("Koregaon", null, null, null);
		Line persistedLine= lineRepo.save(line);
		assertNotNull(persistedLine);
	}
	
	@Test
	void addLines() {
		List<Line> lines = List.of(new Line("Warje", null, null, null),
				new Line("Pimpri", null, null, null),
				new Line("Aundh", null, null, null));
		List<Line> persisted= lineRepo.saveAll(lines);
		assertEquals(persisted.size(),3);
	}

	@Test
	void addLineToDeliveryBoy() {
		Line line= lineRepo.findById(1l).orElse(null);
		DeliveryBoy boy= delboyRepo.findByEmail("sahil@gmail.com").orElse(null);
		boy.setLine(line);
		assertEquals( lineRepo.findById(1l).orElseThrow().getDeliveryBoy().getEmail(), "sahil@gmail.com");
	}
	
	@Test
	void addLineToManager() {
		Manager manager= managerRepo.findByEmail("pankaj@Gmail.com").orElseThrow();
		Line line = lineRepo.findById(1l).orElseThrow();
		line.setManager(manager);
		Line after= lineRepo.findById(1l).orElseThrow();
		assertEquals(after.getManager().getId(), manager.getId());
	}

}
