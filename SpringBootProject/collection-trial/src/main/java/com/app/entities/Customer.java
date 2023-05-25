package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Customer extends User {
	private LocalDate registeredOn;
	@JsonIgnore
	@OneToOne(mappedBy = "customer",fetch = FetchType.EAGER)
	private Subscription subscription;
	
	
	public void addSubscription(Subscription sub) {
		this.setSubscription(sub);
		sub.setCustomer(this);
	}


	public Customer(String firstName, String lastName, String email, String password, Role role, LocalDate registeredOn,
			Subscription subscription) {
		super(firstName, lastName, email, password, role);
		this.registeredOn = registeredOn;
		this.subscription = subscription;
	}
}
