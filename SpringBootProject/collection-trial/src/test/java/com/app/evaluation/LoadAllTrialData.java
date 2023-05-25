package com.app.evaluation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.Address;
import com.app.entities.Bill;
import com.app.entities.Customer;
import com.app.entities.DeliveryBoy;
import com.app.entities.Line;
import com.app.entities.Manager;
import com.app.entities.Role;
import com.app.entities.SubStatus;
import com.app.entities.Subscription;
import com.app.repository.BillRepo;
import com.app.repository.CustomerRepo;
import com.app.repository.DeliveryBoyRepo;
import com.app.repository.LineOrderRepo;
import com.app.repository.LineRepo;
import com.app.repository.ManagerRepo;
import com.app.repository.SubscriptionRepo;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class LoadAllTrialData {
	
	@Autowired
	private DeliveryBoyRepo delBoyRepo;
	
	@Autowired
	private ManagerRepo managerRepo;
	
	@Autowired
	private SubscriptionRepo subscriptionRepo;
	
	@Autowired
	private BillRepo billRepo;
	

	
	@Test
	void loadAllUsers() {
		List<Line> lines = List.of(
				new Line("Koregaon", null, null, null),
				new Line("Warje", null, null, null),
				new Line("Pimpri", null, null, null));
		
		
		
		List<Manager> managers=List.of(
				new Manager("Pankaj", "Patil", "pankaj@gmail.com", "pankaj@123", Role.MANAGER),
				new Manager("Akshay", "Patil", "akshay@gmail.com", "akshay@123", Role.MANAGER),
				new Manager("Amit", "Patil", "amit@gmail.com", "amit@123", Role.MANAGER));
		
		lines.get(0).setManager(managers.get(0));
		lines.get(1).setManager(managers.get(1));
		lines.get(2).setManager(managers.get(2));
		
//		managers.get(0).getLines().add(lines.get(0));
//		managers.get(1).getLines().add(lines.get(1));
//		managers.get(2).getLines().add(lines.get(2));

		
		List<DeliveryBoy> deliveryAgents= List.of(
				new DeliveryBoy("Sahil", "Patil", "sahil@gmail.com", "sahil@123", Role.DELIVERYAGENT, 10000.00, LocalDate.parse("1999-01-01"), LocalDate.parse("2021-05-01"),9960377301l, lines.get(0)),
				new DeliveryBoy("Saurabh", "Shelke", "saurabh@gmail.com", "saurabh@123", Role.DELIVERYAGENT, 10000.00, LocalDate.parse("1998-03-11"), LocalDate.parse("2020-07-01"),7080102030l, lines.get(1)),
				new DeliveryBoy("Sunny", "Gujar", "sunny@gmail.com", "suny@123", Role.DELIVERYAGENT, 10000.00, LocalDate.parse("1993-02-22"), LocalDate.parse("2022-03-01"),8070377301l, lines.get(2))
				);
		
		Customer cmt1= new Customer("Ujwal", "Kelkar", "ujwal@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.parse("2020-02-01"), null);
		Address addrs1 = new Address("Near Sawata Mali Mandir","Vrindavan", "F", "13", "Gavthan", "Manchar", 410503);
		Subscription sbc1=new Subscription(cmt1, "Vrindavan f13", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-03-01"), addrs1, SubStatus.Active, 12, 65, 1, null, null, null, lines.get(0), 1l);
		
		Customer cmt2= new Customer("Suraj", "Teke", "customer2@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.parse("2020-05-01"), null);
		Address addrs2 = new Address("Near Sawata Mali Mandir","SidharthNagar", "A", "10", "Gavthan", "Manchar", 410503);
		Subscription sbc2=new Subscription(cmt2, "SidharthNagar a10", LocalDate.parse("2023-01-01"), LocalDate.parse("2022-08-01"), addrs2, SubStatus.Inactive, 0, 70, 1, null, null, null, lines.get(0), 3l);
		
		Customer cmt3= new Customer("Sagar", "Rane", "customer3@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.parse("2020-06-01"), null);
		Address addrs3 = new Address("Near Post Office","MadhuColony", "C", "1", "Gavthan", "Aundh", 410503);
		Subscription sbc3=new Subscription(cmt3, "MadhuColony c1", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-03-01"), addrs3, SubStatus.Active, 13, 60, 2, null, null, null, lines.get(0), 2l);
		
		Customer cmt4= new Customer("Snehal", "Navale", "customer4@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.now(), null);
		Address addrs4 = new Address("Near Model School","TilakNagar", "B", "4", "Gavthan", "Sangavi", 410503);
		Subscription sbc4=new Subscription(cmt4, "TilakNagar b4", LocalDate.parse("2023-01-01"), null, addrs4, SubStatus.Created, 0, 70, 1, null, null, null, null, -1l);
		
		Customer cmt5= new Customer("Nikhil", "Pawar", "customer5@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.parse("2020-08-01"), null);
		Address addrs5 = new Address("Near Police Station","Paradise", "F", "13", "Gavthan", "Baner", 410503);
		Subscription sbc5=new Subscription(cmt5, "Paradise f13", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-03-01"), addrs5, SubStatus.Active, 15, 65, 1, null, null, null, lines.get(0), 4l);
		
		Customer cmt6= new Customer("Sumedh", "Raut", "customer6@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.parse("2021-01-01"), null);
		Address addrs6 = new Address("Near IT Park","Pearl", "A", "13", "Gavthan", "Magarpatta", 410503);
		Subscription sbc6=new Subscription(cmt6, "Pearl a7", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-03-01"), addrs6, SubStatus.Active, 24, 60, 2, null, null, null, lines.get(1), 1l);
		
		Customer cmt7= new Customer("Omkar", "Joshi", "customer7@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.parse("2029-02-01"), null);
		Address addrs7 = new Address("Near Rainbow Garden","Silvenus", "E", "1", "Gavthan", "Pashan", 410503);
		Subscription sbc7=new Subscription(cmt7, "Silvenus e1", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-03-01"), addrs7, SubStatus.Active, 19, 65, 1, null, null, null, lines.get(1), 3l);
		
		Customer cmt8= new Customer("Yash", "Savant", "customer8@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.now(), null);
		Address addrs8 = new Address("Near Amanora Mall","PWD Colony", "D", "3", "Gavthan", "Panchavati", 410503);
		Subscription sbc8=new Subscription(cmt8, "PWD Colony d3", LocalDate.parse("2023-01-01"), null, addrs8, SubStatus.Created, 0, 67, 1, null, null, null, null, -1l);
		
		Customer cmt9= new Customer("Ankita", "Ghodke", "customer9@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.parse("2021-06-01"), null);
		Address addrs9 = new Address("Near Bus Stand","Nandanvan", "C", "9", "Gavthan", "Shivaji Nagar", 410503);
		Subscription sbc9=new Subscription(cmt9, "Nandanvan c9", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-03-01"), addrs9, SubStatus.Active, 32, 60, 2, null, null, null, lines.get(1), 2l);
		
		Customer cmt10= new Customer("Varsha", "Rane", "customer10@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.now(), null);
		Address addrs10 = new Address("Near Railway Station","Sopan Baug", "B", "7", "Gavthan", "Panchvati", 410503);
		Subscription sbc10=new Subscription(cmt10, "Sopan Baug b7", LocalDate.parse("2023-01-01"), null, addrs10, SubStatus.Created, 0, 60, 1, null, null, null, null, -1l);
		
		Customer cmt11= new Customer("Satyam", "Joshi", "customer11@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.parse("2021-11-01"), null);
		Address addrs11 = new Address("Near Ganpati Temple","Krishna", "F", "13", "Gavthan", "KarveNagar", 410503);
		Subscription sbc11=new Subscription(cmt11, "Krishna f13", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-03-01"), addrs11, SubStatus.Active, 18, 70, 1, null, null, null, lines.get(2), 2l);
		
		Customer cmt12= new Customer("Vaibhav", "Rajguru", "customer12@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.now(), null);
		Address addrs12 = new Address("Near Z.P.School","Mathura", "J", "5", "Gavthan", "Pashan", 410503);
		Subscription sbc12=new Subscription(cmt12, "Mathura j5", LocalDate.parse("2023-01-01"), null, addrs12, SubStatus.Created, 0, 65, 2, null, null, null, null, -1l);
		
		Customer cmt13= new Customer("Shivani", "Dighe", "customer13@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.parse("2020-04-01"), null);
		Address addrs13 = new Address("Near Sawata Mali Mandir","Vrindavan", "a", "2", "Gavthan", "Baner", 410503);
		Subscription sbc13=new Subscription(cmt13, "Vrindavan a2", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-03-01"), addrs13, SubStatus.Active, 14, 70, 1, null, null, null, lines.get(2), 1l);
		
		Customer cmt14= new Customer("Amruta", "Sakure", "customer14@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.parse("2020-06-01"), null);
		Address addrs14 = new Address("Near Hanuman Mandir","SamarthNagar", "B", "6", "Gavthan", "Navi Peth", 410503);
		Subscription sbc14=new Subscription(cmt14, "SamarthNagar B6", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-03-01"), addrs14, SubStatus.Active, 8, 65, 1, null, null, null, lines.get(2), 3l);
		
		Customer cmt15= new Customer("Rohit", "Jadhav", "customer15@gmail.com", "ujwal@123", Role.CUSTOMER, LocalDate.parse("2020-12-01"), null);
		Address addrs15 = new Address("Near Seasons Mall","Diamond", "A", "1", "Gavthan", "Magarpatta", 410503);
		Subscription sbc15=new Subscription(cmt15, "Diamond a1", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-03-01"), addrs15, SubStatus.Active, 6, 60, 2, null, null, null, lines.get(2), 4l);
		
		
		List<Subscription> subscriptions =List.of(sbc1,sbc2,sbc3,sbc4,sbc5,sbc6,sbc7,sbc8,sbc9,sbc10,sbc11,sbc12,sbc13,sbc14,sbc15);
		
		
//		List<Line> persistedLines= lineRepo.saveAll(lines);
//		assertEquals(persistedLines.size(),3);
		
		List<Manager> persistedManagers= managerRepo.saveAll(managers);
		assertEquals(persistedManagers.size(), 3);
		
		List<DeliveryBoy> persistedDeliveryBoys = delBoyRepo.saveAll(deliveryAgents);
		assertEquals(persistedDeliveryBoys.size(), 3);
		
		List<Subscription> persistedSubscriptions = subscriptionRepo.saveAll(subscriptions);
		assertEquals(persistedSubscriptions.size(), 15);
		
		//Add Bills
		
		List<Subscription> subs= subscriptionRepo.findAll();
		if(subs.size()>3) {
			List<Bill> bills=List.of(
					new Bill(LocalDate.parse("2022-05-01"), LocalDate.parse("2022-05-31"), 4, 7, 2060, true, LocalDate.parse("2022-06-05"),subs.get(0)),
					new Bill(LocalDate.parse("2022-06-01"), LocalDate.parse("2022-06-30"), 3, 5, 1940, true, LocalDate.parse("2022-07-05"),subs.get(0)),
					new Bill(LocalDate.parse("2022-07-01"), LocalDate.parse("2022-07-31"), 6, 10, 2120, true, LocalDate.parse("2022-08-05"),subs.get(0)),
					new Bill(LocalDate.parse("2022-08-01"), LocalDate.parse("2022-08-31"), 2, 3, 1880, true, LocalDate.parse("2022-09-05"), subs.get(0)),
					new Bill(LocalDate.parse("2022-09-01"), LocalDate.parse("2022-09-30"), 5, 4, 2400, true, LocalDate.parse("2022-10-05"), subs.get(0)),
					new Bill(LocalDate.parse("2023-02-01"), LocalDate.parse("2023-02-28"), 5, 4, 2400, false, null, subs.get(0)),
					new Bill(LocalDate.parse("2022-05-01"), LocalDate.parse("2022-05-31"), 4, 7, 2060, true, LocalDate.parse("2022-06-05"), subs.get(1)),
					new Bill(LocalDate.parse("2022-06-01"), LocalDate.parse("2022-06-30"), 3, 5, 1940, true, LocalDate.parse("2022-07-05"), subs.get(1)),
					new Bill(LocalDate.parse("2022-07-01"), LocalDate.parse("2022-07-31"), 6, 10, 2120, true, LocalDate.parse("2022-08-05"), subs.get(1)),
					new Bill(LocalDate.parse("2022-08-01"), LocalDate.parse("2022-08-31"), 2, 3, 1880, true, LocalDate.parse("2022-09-05"), subs.get(1)),
					new Bill(LocalDate.parse("2022-09-01"), LocalDate.parse("2022-09-30"), 5, 4, 2400, true, LocalDate.parse("2022-10-05"), subs.get(1)),
					new Bill(LocalDate.parse("2023-02-01"), LocalDate.parse("2023-02-28"), 5, 4, 2400, false, null, subs.get(1)),
					new Bill(LocalDate.parse("2022-05-01"), LocalDate.parse("2022-05-31"), 4, 7, 2060, true, LocalDate.parse("2022-06-05"), subs.get(2)),
					new Bill(LocalDate.parse("2022-06-01"), LocalDate.parse("2022-06-30"), 3, 5, 1940, true, LocalDate.parse("2022-07-05"), subs.get(2)),
					new Bill(LocalDate.parse("2022-07-01"), LocalDate.parse("2022-07-31"), 6, 10, 2120, true, LocalDate.parse("2022-08-05"), subs.get(2)),
					new Bill(LocalDate.parse("2022-08-01"), LocalDate.parse("2022-08-31"), 2, 3, 1880, true, LocalDate.parse("2022-09-05"), subs.get(2)),
					new Bill(LocalDate.parse("2022-09-01"), LocalDate.parse("2022-09-30"), 5, 4, 2400, true, LocalDate.parse("2022-10-05"), subs.get(2)),
					new Bill(LocalDate.parse("2023-02-01"), LocalDate.parse("2023-02-28"), 5, 4, 2400, false, null, subs.get(2))
					);
			List<Bill> persistedBills= billRepo.saveAll(bills);
			assertEquals(18, persistedBills.size());
			assertNotNull(persistedBills.get(0).getId());
		}
		
	}

}
