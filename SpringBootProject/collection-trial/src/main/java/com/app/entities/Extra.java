package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
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
public class Extra extends BaseEntity {
	private LocalDate extraDate;
	private boolean isDelivered;
	private int packetsCount;
	@ManyToOne
	private Subscription subscription;
}
