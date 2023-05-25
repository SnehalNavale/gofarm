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
public class Bill extends BaseEntity{
	private LocalDate startDate;
	private LocalDate endDate;
	private int leaves;
	private int extraCount;
	private double billAmount;
	private boolean isPaid;
	private LocalDate paidOn;
	@ManyToOne
	private Subscription subscription;
}
