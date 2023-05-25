package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class LineOrder extends BaseEntity {
	private LocalDate orderDate;
	
	@ManyToOne
	@JoinColumn(name = "line_id")
	private Line lineNo;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lineorder")
	private List<Delivery> deliveryList = new ArrayList<>();
	
	public void addOrder(Delivery delivery) {
		deliveryList.add(delivery);
		delivery.setLineorder(this);
	}
	public void removeOrder(Delivery delivery) {
		deliveryList.remove(delivery);
		delivery.setLineorder(null);
	}

}
