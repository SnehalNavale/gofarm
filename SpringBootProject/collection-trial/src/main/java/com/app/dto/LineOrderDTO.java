package com.app.dto;

import java.time.LocalDate;

import com.app.entities.LineOrder;
import com.app.entities.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineOrderDTO {
	private Long id;
	private LocalDate orderDate;
	private String lineNo;
	private Status status;
	
	public LineOrderDTO(LineOrder order) {
		id=order.getId();
		orderDate=order.getOrderDate();
		lineNo=order.getLineNo().getLineName();
		status= order.getStatus();
	}

}
