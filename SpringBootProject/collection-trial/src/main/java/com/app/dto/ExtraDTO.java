package com.app.dto;

import java.time.LocalDate;

import com.app.entities.Extra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtraDTO {
	private Long id;
	private LocalDate extraDate;
	private boolean isDelivered;
	private int packetsCount;
	
	public ExtraDTO (Extra extra) {
		id=extra.getId();
		extraDate=extra.getExtraDate();
		isDelivered=extra.isDelivered();
		packetsCount=extra.getPacketsCount();
	}
}
