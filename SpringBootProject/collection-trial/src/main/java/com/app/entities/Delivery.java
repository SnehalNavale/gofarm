package com.app.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Delivery extends BaseEntity {
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subcription_id")
	private Subscription subscrip;
	private int noOfPackets;
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;
	@ManyToOne
	@JoinColumn(name = "lineOrder_id")
	private LineOrder lineorder;
}
