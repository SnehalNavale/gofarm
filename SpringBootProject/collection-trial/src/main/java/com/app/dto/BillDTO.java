package com.app.dto;

import java.time.LocalDate;

import com.app.entities.Bill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
	private Long id;
	private LocalDate startDate;
	private LocalDate endDate;
	private int leaves;
	private int extraCount;
	private double billAmount;
	private boolean isPaid;
	private LocalDate paidOn;
	private String alias;
	
	public BillDTO(Bill bill) {
		id=bill.getId();
		startDate=bill.getStartDate();
		endDate=bill.getEndDate();
		leaves=bill.getLeaves();
		extraCount=bill.getExtraCount();
		billAmount=bill.getBillAmount();
		isPaid=bill.isPaid();
		paidOn=bill.getPaidOn();
		alias=bill.getSubscription().getAlias();		
	}

}
