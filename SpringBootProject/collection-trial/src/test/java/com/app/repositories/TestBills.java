package com.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.Bill;
import com.app.entities.Subscription;
import com.app.repository.BillRepo;
import com.app.repository.SubscriptionRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class TestBills {

	@Autowired
	private BillRepo billRepo;

	@Autowired
	private SubscriptionRepo subscriptionRepo;

	@Test
	void testAddBills() {
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
