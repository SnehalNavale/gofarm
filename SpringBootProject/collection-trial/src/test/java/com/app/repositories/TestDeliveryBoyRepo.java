package com.app.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.DeliveryBoy;
import com.app.entities.Line;
import com.app.entities.Role;
import com.app.repository.DeliveryBoyRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class TestDeliveryBoyRepo {
	
	@Autowired
	private DeliveryBoyRepo delBoyRepo;

//	@Test
//	void addCustomers() {
//		Customer cmt= new Customer("Ujwal", "Kelkar", "ujwal@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.now(), null);
//		Address addrs = new Address("Near Sawata Mali Mandir",
//				"Vrindavan", "F", "13", "Gavthan", "Manchar", 410503, null);
//		assertEquals(cmt.getRole(), Role.CUSTOMER);
//		Subscription sbc=new Subscription(cmt, "Vrindavan f13", LocalDate.parse("2023-01-01"), null, addrs, SubStatus.Created, 0, 60, null, null, null, null, -1l);
//		Subscription after= subsRepo.save(sbc);
//		assertNotNull(after.getId());
//		assertEquals(after.getAddress().getPinCode(),410503);
//		assertNotNull(after.getId());
//	}
	
	@Test
	void addDeliveryBoy() {
		//Line line = new Line("Koregaon Park", null, null, null);
		DeliveryBoy dboy= new DeliveryBoy("Sahil", "Patil", "sahil@gmail.com", "sahil@123", Role.DELIVERYAGENT, 10000.00, LocalDate.parse("1999-01-01"), LocalDate.parse("2021-05-01"),9960377301l, null);
		DeliveryBoy persistedDBoy= delBoyRepo.save(dboy);
		assertNotNull(persistedDBoy);
	}
	
	@Test
	void findDeliveryBoy() {
		DeliveryBoy dboy= delBoyRepo.findByEmail("sahil@gmail.com").orElseThrow();
		System.out.println(dboy.getFirstName()+", "+dboy.getLastName());
		assertNotNull(dboy);	
	}
	
	@Test
	void deleteDeliveryBoy() {
		DeliveryBoy dboy= delBoyRepo.findById(1l).orElse(null);
		delBoyRepo.delete(dboy);
		assertTrue(delBoyRepo.findById(1l).isEmpty());
	}
	
	

}
