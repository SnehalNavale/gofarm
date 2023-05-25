package com.app.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class DeliveryBoy extends User{
	private Double salary;
	private LocalDate dateOfBirth;
	private LocalDate joiningDate;
	private Long contactNumber;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Line line;
	
	public DeliveryBoy(String firstName, String lastName, String email, String password, Role role, Double salary,
			LocalDate dateOfBirth, LocalDate joiningDate, Long contactNumber, Line line) {
		super(firstName, lastName, email, password, role);
		this.salary = salary;
		this.dateOfBirth = dateOfBirth;
		this.joiningDate = joiningDate;
		this.contactNumber = contactNumber;
		this.line = line;
	}	
	
	
}
