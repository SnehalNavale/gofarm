package com.app.dto;

import com.app.entities.Delivery;
import com.app.entities.DeliveryStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeliveryDTO {
	private Long id;
	private Long sequencer;
	private String alias;
	private int noOfPackets;
	private DeliveryStatus status;
	
	public DeliveryDTO(Delivery delivery) {
		id=delivery.getId();
		sequencer=delivery.getSubscrip().getSequencer();
		alias=delivery.getSubscrip().getAlias();
		noOfPackets=delivery.getNoOfPackets();
		status=delivery.getStatus();
	}

}
