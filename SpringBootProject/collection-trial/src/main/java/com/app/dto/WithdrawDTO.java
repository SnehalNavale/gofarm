package com.app.dto;

import java.time.LocalDate;

import com.app.entities.Withdraw;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawDTO {
	private Long id;
	private LocalDate leaveDate;
	private boolean isValid;
	private boolean isFulfilled;
	private String alias;
	private String lineName;
	
	public WithdrawDTO(Withdraw leave) {
		id=leave.getId();
		leaveDate=leave.getLeaveDate();
		isFulfilled=leave.isFulfilled();
		isValid=leave.isValid();
		alias=leave.getSubscription().getAlias();
		lineName=leave.getSubscription().getLine().getLineName();
	}

}
