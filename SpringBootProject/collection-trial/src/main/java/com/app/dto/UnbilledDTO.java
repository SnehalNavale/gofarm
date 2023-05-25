package com.app.dto;

import java.time.LocalDate;

import com.app.entities.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnbilledDTO {
	private Long id;
	private LocalDate startDate;
	private int extras;
	private int leaves;
	private int delivered;
	private double amount;
	public UnbilledDTO(Customer cust) {
		id=cust.getSubscription().getId();
		startDate=cust.getSubscription().getLastBilledOn().plusDays(1);
		extras=cust.getSubscription().getExtras().size();
		leaves=cust.getSubscription().getWithdraws().size();
		delivered=cust.getSubscription().getPacketsDelivered();
		amount=cust.getSubscription().getPacketsDelivered() * cust.getSubscription().getRatePerPacket();
	}
}
