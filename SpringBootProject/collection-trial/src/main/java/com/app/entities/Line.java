package com.app.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Line extends BaseEntity {
	private String lineName;
	@ManyToOne
//	@JoinColumn(name = "manager_id")
	private Manager manager;
	@OneToOne(mappedBy = "line")
	private DeliveryBoy deliveryBoy;
	@OneToMany(mappedBy = "line",cascade = CascadeType.ALL, orphanRemoval = true ,fetch = FetchType.EAGER)
	private List<Subscription> list;
	
	public void addDeliveryBoy(DeliveryBoy db) {
		this.setDeliveryBoy(db);
		db.setLine(this);
	}
	
	public void removeDeliveryBoy(DeliveryBoy db) {
		db.setLine(null);
		this.setDeliveryBoy(null);
	}
	
	public void addSubscription(Subscription sub) {
		list.add(sub);
		sub.setLine(this);
	}
	public void removeSubscription(Subscription sub) {
		list.remove(sub);
		sub.setLine(null);
	}
	
}
