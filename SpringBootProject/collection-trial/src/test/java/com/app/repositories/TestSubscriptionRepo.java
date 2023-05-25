package com.app.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.Address;
import com.app.entities.Bill;
import com.app.entities.Customer;
import com.app.entities.Role;
import com.app.entities.SubStatus;
import com.app.entities.Subscription;
import com.app.repository.CustomerRepo;
import com.app.repository.SubscriptionRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class TestSubscriptionRepo {
	
	@Autowired
	private SubscriptionRepo subsRepo;
	
	@Autowired
	private CustomerRepo custRepo;

	@Test
	void addCustomers() {
		Customer cmt= new Customer("Ujwal", "Kelkar", "ujwal@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.now(), null);
		Address addrs = new Address("Near Sawata Mali Mandir",
				"Vrindavan", "F", "13", "Gavthan", "Manchar", 410503);
		assertEquals(cmt.getRole(), Role.CUSTOMER);
		Subscription sbc=new Subscription(cmt, "Vrindavan f13", LocalDate.parse("2023-01-01"), null, addrs, SubStatus.Created, 0, 60, 1, null, null, null, null, -1l);
		Subscription after= subsRepo.save(sbc);
		assertNotNull(after.getId());
		assertEquals(after.getAddress().getPinCode(),410503);
		assertNotNull(after.getId());
	}
	
	@Test
	void findSubscriptyionById() {
		Subscription sub= subsRepo.findById(1l).orElse(null);
		assertNotNull(sub);	
	}
	
	@Test
	void deleteSubscription() {
		Subscription sub= subsRepo.findById(1l).orElse(null);
		subsRepo.delete(sub);
		assertTrue(subsRepo.findById(1l).isEmpty());
	}
	
	@Test
	void getCustomerFromSubscription() {
		
		Customer cust=custRepo.findByEmail("ujwal@gmail.com").orElse(null);
		System.out.println("Returned customer is "+cust.getFirstName()+" "+cust.getLastName());
		System.out.println("Subscription Id of customer is "+cust.getSubscription().getId());
		assertNotNull(cust);
	}
	
	@Test
	void addBillsToSubscription() {
		Customer cust= custRepo.findByEmail("ujwal@gmail.com").orElseThrow();
		Bill bill = new Bill(LocalDate.parse("2022-05-01"), LocalDate.parse("2022-05-31"),4,7,4000, true, LocalDate.parse("2022-06-03"), cust.getSubscription());
		cust.getSubscription().getBills().add(bill);
		Bill bill1 = new Bill(LocalDate.parse("2022-06-01"), LocalDate.parse("2022-06-30"),10,30,5000, true, LocalDate.parse("2022-07-03"), cust.getSubscription());
		cust.getSubscription().getBills().add(bill1);
		cust.getSubscription().setLastBilledOn(LocalDate.parse("2023-02-15"));
		cust.getSubscription().setPacketsDelivered(28);
		cust.getSubscription().setSubStatus(SubStatus.Active);
		Subscription sub = subsRepo.findById(1l).orElseThrow();
		//Customer cust2=custRepo.save(cust);
		assertEquals(SubStatus.Active, sub.getSubStatus());
	}

}
