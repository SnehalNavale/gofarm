package com.app.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.Line;
import com.app.entities.Manager;
import com.app.entities.Role;
import com.app.repository.LineRepo;
import com.app.repository.ManagerRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class TestManagerRepo {
	
	@Autowired
	private ManagerRepo managerRepo;
	
	@Autowired
	private LineRepo lineRepo;
	
	@Test
	void addManager() {
		Manager manger= new Manager("Pankaj", "Patil", "pankaj@gmail.com", "pankaj@123", Role.MANAGER);
		Manager persistedManager= managerRepo.save(manger);
		assertNotNull(persistedManager);
	}
	
	@Test
	void addMoreManagers() {
		List<Manager> managers=List.of(
				new Manager("Akshay", "Patil", "akshay@gmail.com", "akshay@123", Role.MANAGER),
				new Manager("Amit", "Patil", "amit@gmail.com", "amit@123", Role.MANAGER));
		List<Manager> persisted= managerRepo.saveAll(managers);
		assertEquals(persisted.size(), 2);
	}
	
	@Test
	void findManager() {
		Manager manager= managerRepo.findById(1l).orElse(null);
		assertNotNull(manager);	
	}
	
	@Test
	void deleteManager() {
		Manager manager= managerRepo.findById(1l).orElse(null);
		managerRepo.delete(manager);
		assertTrue(managerRepo.findById(1l).isEmpty());
	}

}
